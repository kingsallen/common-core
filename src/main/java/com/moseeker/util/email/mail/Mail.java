package com.moseeker.util.email.mail;


import com.moseeker.constant.Constant;
import com.moseeker.util.ConfigPropertiesUtil;
import com.moseeker.util.SpringBeanUtil;
import com.moseeker.util.StringUtils;
import com.moseeker.util.email.attachment.Attachment;
import com.moseeker.util.email.config.EmailContent;
import com.moseeker.util.email.config.EmailSessionConfig;
import com.moseeker.util.email.config.ServerConfig;
import org.apache.axis.attachments.ImageDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by chendi on 3/31/16.
 * 
 * 基于迪迪在3月31号提交的email发送，将一些可配置信息提出作为参数设置，即可以配置指定邮件服务器。并为发送邮件添加了一个线程池。
 * <p>Company: MoSeeker</P>  
 * <p>date: Sep 21, 2016</p>  
 */

public class Mail {

    private static Environment environment = SpringBeanUtil.getBean(Environment.class);

    private static Logger logger = LoggerFactory.getLogger(Mail.class);
	
	private static final String serverDomain = environment.getProperty("email.serverDomain", String.class);
    private static final Integer serverPort = environment.getProperty("email.serverPort", Integer.class);
    private static final String userName = environment.getProperty("email.userName", String.class);
    private static final String password = environment.getProperty("email.password", String.class);
    private static final String sender = environment.getProperty("email.senderAddress", String.class);

    private final Message message;				//邮件
    private final ServerConfig serverConfig;			//服务器配置
    
    //利用构造者模式创建邮件类
    public Mail(MailBuilder builder) {
        this.message = builder.message;
        this.serverConfig = builder.serverConfig;
    }
    
    //将构造好的邮件发送到邮件服务器
    public void send() {
		 try {
			Transport transport = this.message.getSession().getTransport();
			    try {
			        transport.connect(serverConfig.getHost(), serverConfig.getPort(), serverConfig.getUsername(), serverConfig.getPassword());
			        transport.sendMessage(this.message, this.message.getAllRecipients());
                    logger.info("send email success from:"+message.getFrom() +" to:"+message.getAllRecipients()+" topic:"+message.getSubject());
			    } catch (Exception e) {
			        logger.error("from:"+message.getFrom() +" to:"+message.getAllRecipients()+" topic:"+message.getSubject()+e.getMessage(),e);
                } finally{
			        transport.close();
			        logger.info("from:"+message.getFrom() +" to:"+message.getAllRecipients()+" topic:"+message.getSubject());
			    }
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
    }
    
    /**
     * 发送一封邮件
     * @param emailContent 邮件内容
     * @throws AddressException 邮件地址异常
     * @throws MessagingException	邮件消息异常
     * @throws IOException	IO相关的异常
     */
    public void send(EmailContent emailContent) throws AddressException, MessagingException, IOException {
		try {
			buildHeader(message, emailContent);
		    buildContent(message, emailContent);
		    buildAttachment(message, emailContent);
		    message.saveChanges();
			Transport transport = this.message.getSession().getTransport();
			    try {
			        transport.connect(serverConfig.getHost(), serverConfig.getPort(), serverConfig.getUsername(), serverConfig.getPassword());
			        transport.sendMessage(this.message, this.message.getAllRecipients());
			    } finally {
			        transport.close();
			        logger.info("from:"+emailContent.getSenderName() +" to:"+emailContent.getRecipients()+" topic:"+emailContent.getSubject());
			    }
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
    }
    
    /**
     * 
     * 邮件构造者
     * <p>Company: MoSeeker</P>  
     * <p>date: Sep 21, 2016</p>  
     * <p>Email: wjf2255@gmail.com</p>
     * @author wjf
     * @version
     */
    public static class MailBuilder {

        private Message message;					//邮件消息
        private ServerConfig serverConfig;			//服务器配置信息
        private EmailSessionConfig sessionConfig;	//连接配置
        
        /**
         * 根据默认邮件服务器生成邮件构造器
         * @throws MessagingException
         */
        public MailBuilder() throws MessagingException {
        	serverConfig = new ServerConfig(serverDomain, serverPort, userName, password);
        }

        /**
         * 利用指定邮件服务器配置信息 生成邮件构造器
         * @param serverConfig
         * @throws MessagingException
         */
        public MailBuilder(ServerConfig serverConfig) throws MessagingException {
            this.serverConfig = serverConfig;
        }
        
        /**
         * 配置session信息
         * @param sessionConfig
         * @return
         */
        public MailBuilder buildSessionConfig(EmailSessionConfig sessionConfig) {
            this.sessionConfig = sessionConfig;
            return this;
        }
        
        /**
         * 配置邮件内容
         * @param emailContent
         * @return
         * @throws Exception
         */
        public MailBuilder buildEmailContent(EmailContent emailContent) throws Exception {
        	if(message == null) {
        		initMessage();
        	}
        	buildHeader(emailContent).buildContent(emailContent).buildAttachment(emailContent);
        	this.message.saveChanges();
            return this;
        }
        
        /**
         * 创建邮件
         * @param emailContent
         * @return
         * @throws IOException 
         * @throws Exception
         */
        public Mail build(EmailContent emailContent) throws IOException, Exception {
        	if(message == null) {
        		initMessage();
        	}
            this.buildHeader(emailContent).buildContent(emailContent).buildImage(emailContent).buildAttachment(emailContent);
            this.message.saveChanges();
            return new Mail(this);
        }
        
        /**
         * 创建邮件
         * @return
         * @throws MessagingException 
         * @throws Exception
         */
        public Mail buildMailServer() throws MessagingException {
        	if(message == null) {
        		initMessage();
        	}
            this.message.saveChanges();
            return new Mail(this);
        }

        /**
         * 创建邮件标题、发件人、收件人
         * @param emailContent
         * @return
         * @throws MessagingException 
         * @throws AddressException 
         * @throws UnsupportedEncodingException 
         * @throws Exception
         */
        private MailBuilder buildHeader(EmailContent emailContent) throws AddressException, MessagingException, UnsupportedEncodingException {
        	Mail.buildHeader(this.message, emailContent);
            return this;
        }

        /**
         * 创建邮件内容
         * @param emailContent
         * @return
         * @throws MessagingException 
         * @throws IOException 
         * @throws Exception
         */
        private MailBuilder buildContent(EmailContent emailContent) throws IOException, MessagingException {
            Mail.buildContent(message, emailContent);
            return this;
        }

        /**
         * 创建附件
         * @param emailContent
         * @return
         * @throws Exception
         */
        private MailBuilder buildAttachment(EmailContent emailContent) throws Exception {
        	Mail.buildAttachment(message, emailContent);
            return this;
        }

        private MailBuilder buildImage(EmailContent emailContent) throws Exception {
            Mail.buildImage(message, emailContent);
            return this;
        }

        /**
         * 初始化邮件内容
         * @return
         * @throws MessagingException
         */
        private Message initMessage() throws MessagingException {
            Properties properties = new Properties();
            if(sessionConfig == null) {
            	properties.setProperty("mail.smtp.auth", "true");
                properties.setProperty("mail.transport.protocol", "smtp");
            } else {
            	properties.setProperty("mail.smtp.auth", String.valueOf(sessionConfig.isAuth()));
                properties.setProperty("mail.transport.protocol", sessionConfig.getProtocol());
            }
            Session session = Session.getDefaultInstance(properties);
            message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart("mixed");
            message.setContent(multipart);
            return message;
        }
    }
   
    /**
     * 创建邮件标题、收件人、发件人
     * @param message
     * @param emailContent
     * @throws AddressException
     * @throws MessagingException
     * @throws UnsupportedEncodingException 
     */
    private static void buildHeader(Message message, EmailContent emailContent) throws AddressException, MessagingException, UnsupportedEncodingException {
    	if(StringUtils.isNullOrEmpty(emailContent.getSenderName())) {
    		message.setFrom(new InternetAddress(sender));
    	} else {
    		if(message == null) {
    		    logger.info("message is null!");
    		}
    		logger.info("message is null! {} {}", emailContent.getSenderName(), emailContent.getSenderDisplay());
    		message.setFrom(new InternetAddress(emailContent.getSenderName(), emailContent.getSenderDisplay()));
    	}
    	if(StringUtils.isNotNullOrEmpty(emailContent.getSubject())) {
    		message.setSubject(emailContent.getSubject());
    	} else {
    		message.setSubject(Constant.EMAIL_VERIFIED_SUBJECT);
    	}
    	ArrayList<InternetAddress> recipients = new ArrayList<>();
    	for (String recipient : emailContent.getRecipients()) {
    		recipients.add(new InternetAddress(recipient));
    	}
    	message.setRecipients(RecipientType.TO, recipients.toArray(new InternetAddress[emailContent.getRecipients().size()]));
    }
    
    /**
     * 配置邮件内容
     * @param message
     * @param emailContent
     * @throws IOException
     * @throws MessagingException
     */
    private static void buildContent(Message message, EmailContent emailContent) throws IOException, MessagingException {
    	 Multipart content = (Multipart) message.getContent();
         MimeBodyPart body = new MimeBodyPart();
         body.setContentID("emailText");
         content.addBodyPart(body);
         body.setText(emailContent.getContent(), emailContent.getCharset(), emailContent.getSubType());
    }
    
    /**
     * 配置附件信息
     * @param message
     * @param emailContent
     * @throws IOException
     * @throws MessagingException
     */
    private static void buildAttachment(Message message, EmailContent emailContent) throws IOException, MessagingException {
        if(emailContent.getAttachments() != null) {
        	Multipart content = (Multipart) message.getContent();
        	for (Attachment attachment : emailContent.getAttachments()) {
                content.addBodyPart(attachment.getAttachment());
            }
        }
    }

    private static void buildImage(Message message, EmailContent emailContent) throws Exception {
        MimeBodyPart image = new MimeBodyPart();
        DataHandler dh = new DataHandler(new ImageDataSource("name",emailContent.getImage()));
        image.setDataHandler(dh);
        image.setContentID("emailImage");

        MimeMultipart content = (MimeMultipart) message.getContent();
        BodyPart text = content.getBodyPart("emailText");
        content.removeBodyPart(text);

        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(text);
        multipart.addBodyPart(image);
        multipart.setSubType("related");

        MimeBodyPart contentText =  new MimeBodyPart();
        contentText.setContent(multipart);
        content.addBodyPart(contentText);
    }
}
