package com.moseeker.util.email;

import com.moseeker.util.SpringBeanUtil;
import com.moseeker.util.email.attachment.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.event.ConnectionListener;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by chendi on 3/31/16.
 */

public class Email {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static Environment environment = SpringBeanUtil.getBean(Environment.class);

    private static String serverDomain = environment.getProperty("email.serverDomain", String.class);     //= propertiesReader.get("email.serverDomain", String.class);

    private static Integer serverPort = environment.getProperty("email.serverPort", Integer.class);     //= propertiesReader.get("email.serverPort", Integer.class);

    private static String userName = environment.getProperty("email.userName", String.class); ;//= propertiesReader.get("email.userName", String.class

    private static String password = environment.getProperty("email.password", String.class) ;//= propertiesReader.get("email.password", String.class);

    private Message message;

    public Email(EmailBuilder builder) {
        this.message = builder.message;
    }

    public interface EmailListener {
        void success();

        void failed(Exception e);
    }

    public void send() {
        send(null, null);

    }

    public void send(int retryTimes) {
        send(retryTimes, null);
    }

    public void send(int retryTimes, EmailListener listener) {
        send(new TransportListener() {
            int i = retryTimes;//重试三次邮件

            @Override
            public void messageDelivered(TransportEvent e) {
                if (listener != null) {
                    listener.success();
                }
            }

            @Override
            public void messageNotDelivered(TransportEvent e) {
                retry();
            }

            @Override
            public void messagePartiallyDelivered(TransportEvent e) {
                retry();
            }

            void retry() {
                if (i > 0) {
                    logger.info("send mail failed,retry at:{}", i);
                    send(this);
                    i--;
                } else {
                    logger.info("send mail failed after {} times retry", retryTimes);
                    if (listener != null) {
                        listener.failed(new ConnectException("cannot send email after " + retryTimes + "times retry!"));
                    }
                }
            }
        });
    }

    public void send(TransportListener transportListener) {
        send(null, transportListener);
    }

    public void send(ConnectionListener connectionListener, TransportListener transportListener) {

        new Thread(() -> {
            try {
                Transport transport = this.message.getSession().getTransport();
                try {
                    if (connectionListener != null) {
                        transport.addConnectionListener(connectionListener);
                    }
                    if (transportListener != null) {
                        transport.addTransportListener(transportListener);
                    }
                    transport.connect(serverDomain, serverPort, userName, password);
                    transport.sendMessage(this.message, this.message.getAllRecipients());
                } finally {
                    transport.close();
                }
            } catch (Exception e) {
                if (transportListener != null) {
                    transportListener.messageNotDelivered(null);
                }
                e.printStackTrace();
            }
        }).start();
    }

    public static class EmailBuilder {

        private static Environment environment = SpringBeanUtil.getBean(Environment.class);
        // required
        private ArrayList<String> recipients = new ArrayList<>();
        private ArrayList<String> ccList = new ArrayList<>();

        // optional
        private String senderAddress = environment.getProperty("email.senderAddress", String.class);
        private String subject = "";
        private String content = "";
        private ArrayList<Attachment> attachments = new ArrayList<>();

        private Message message;

        public EmailBuilder(List<String> recipients) throws MessagingException {
            this.recipients.addAll(recipients);
        }

        public EmailBuilder(String recipient) throws MessagingException {
            this.recipients.add(recipient);
        }

        public EmailBuilder setSender(String sender) {
            this.senderAddress = sender;
            return this;
        }

        public EmailBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public EmailBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public EmailBuilder addRecipient(String recipient) {
            this.recipients.add(recipient);
            return this;
        }

        public EmailBuilder addRecipients(List<String> recipients) {
            this.recipients.addAll(recipients);
            return this;
        }

        public EmailBuilder addCCList(List<String> ccList) {
            this.ccList.addAll(ccList);
            return this;
        }

        public EmailBuilder addCC(String cc) {
            this.ccList.add(cc);
            return this;
        }

        public EmailBuilder addAttachment(Attachment attachment) {
            this.attachments.add(attachment);
            return this;
        }

        public Email build() throws Exception {
            this.message = this.initMessage();
            this.buildHeader().buildContent().buildAttachment();
            this.message.saveChanges();
            return new Email(this);
        }

        private EmailBuilder buildHeader() throws Exception {
            this.message.setFrom(new InternetAddress(this.senderAddress));
            this.message.setSubject(this.subject);
            ArrayList<InternetAddress> recipients = new ArrayList<>();
            for (String recipient : this.recipients) {
                recipients.add(new InternetAddress(recipient));
            }
            ArrayList<InternetAddress> ccList = new ArrayList<>();
            for(String cc : this.ccList){
                ccList.add(new InternetAddress(cc));
            }
            this.message.setRecipients(RecipientType.TO, recipients.toArray(new InternetAddress[this.recipients.size()]));
            if(ccList.size() > 0) {
                this.message.setRecipients(RecipientType.CC, ccList.toArray(new InternetAddress[this.ccList.size()]));
            }
            return this;
        }

        private EmailBuilder buildContent() throws Exception {
            Multipart content = (Multipart) this.message.getContent();
            MimeBodyPart body = new MimeBodyPart();
            content.addBodyPart(body);
            body.setText(this.content, "utf-8", "html");
            return this;
        }

        private EmailBuilder buildAttachment() throws Exception {
            Multipart content = (Multipart) this.message.getContent();
            for (Attachment attachment : this.attachments) {
                content.addBodyPart(attachment.getAttachment());
            }
            return this;
        }

        private Message initMessage() throws MessagingException {
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.transport.protocol", "smtp");
            Session session = Session.getDefaultInstance(properties);
            Message message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart("mixed");
            message.setContent(multipart);
            return message;
        }
    }
}
