package com.moseeker.util;

import com.moseeker.util.email.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 猎头邮件发送工具
 *
 * @Author: lee
 * @Date: 2019/2/27
 */
@Component
@Slf4j
public class HeadhunterEmailUtil {

    /**
    * 发送猎头邮件
    *
    * @param emailAddress 多个邮箱之间，用`,`隔开
    * @return
    * @exception
    * @Author  lee
    * @Date  2019/2/27 下午2:40
    */
    public static boolean sendHeadhunterEmail(String emailAddress, String emailContent, String emailSubject) {
        try {
            Email.EmailBuilder emailBuilder = new Email.EmailBuilder(Arrays.asList(emailAddress.split("[,，]")));
            emailBuilder.setContent(emailContent);
            emailBuilder.setSubject(emailSubject);
            Email email = emailBuilder.build();
            email.send(3, new Email.EmailListener() {
                @Override
                public void success() {
                    log.info("email send 【猎头渠道邮件】");
                }

                @Override
                public void failed(Exception e) {
                    log.error("猎头渠道邮件发送失败：{}", e.getMessage());
                }
            });
            return true;
        } catch (Exception e) {
            log.error("===============猎头渠道邮件发送失败，emailAddress:{}=================", emailAddress);
            e.printStackTrace();
        }
        return false;
    }
}
