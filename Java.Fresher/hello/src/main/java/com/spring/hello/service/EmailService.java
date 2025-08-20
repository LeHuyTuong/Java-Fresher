package com.spring.hello.service;

import com.spring.hello.entity.mail.EmailEntity;

public interface EmailService {

    String sendTextEmail(EmailEntity email);
    String sendHtmlEmail(EmailEntity email);
    String sendMailAttachmentEmail(EmailEntity email);

}
