package com.tuonglh.coffee.samplecode.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.from}")
    private String emailFrom;

    public String sendMail(String recipients, String subject , String content, MultipartFile[] files) throws MessagingException, UnsupportedEncodingException {
        log.info("Sending mail...");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(emailFrom , "Tuong SE19");  // thay monsterminecraft2005@gmail thanhg tuongse19

        if(recipients.contains(",")){      // nếu có nhiều email thì sẽ parse ra
            helper.setTo(InternetAddress.parse(recipients));
        }else{
            helper.setTo(recipients);
        }

        if(files != null){
            // duyet file
            for(MultipartFile file : files){
                helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);
            }
        }

        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);

        log.info("Mail sent! , recipients = {}" , recipients);
        return "sent";
    }

    public void sendConfirmLink(String emailTo, Long userId, String secretCode) throws MessagingException, UnsupportedEncodingException {
        log.info("Sending mail confirm account");

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Context context = new Context();

        String linkConfirm = String.format("http://localhost:80/user/confirm/%s?secretCode=%s", userId, secretCode);

        Map<String,Object> properties = new HashMap<>();
        properties.put("linkConfirm", linkConfirm);
        context.setVariables(properties);

        helper.setFrom(emailFrom, "Tuong SE");
        helper.setTo(emailTo);
        helper.setSubject("Please confirm your account");

        String html = templateEngine.process("confirmEmail.html",context); // context hien tai dang luu gia tri linkConfirm cua map
        helper.setText(html, true);
        mailSender.send(message);
        log.info("Mail sent! , emailTo = {}" , emailTo);
    }

    @KafkaListener(topics = "confirm-account-topic", groupId = "confirm-account-group") // kafka lắng nghe
    private void sendConfirmLinkByKafka(String message) throws MessagingException, UnsupportedEncodingException {
        log.info("Sending link  confirm user ...");

        String[] arr =  message.split(",");
        String emailTo = arr[0];
        String userId = arr[1];
        String verifyCode = arr[2];
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Context context = new Context();

        String linkConfirm = String.format("http://localhost:80/user/confirm/%s?secretCode=%s", userId, verifyCode);

        Map<String,Object> properties = new HashMap<>();
        properties.put("linkConfirm", linkConfirm);
        context.setVariables(properties);

        helper.setFrom(emailFrom, "Tuong SE");
        helper.setTo(emailTo);
        helper.setSubject("Please confirm your account");

        String html = templateEngine.process("confirmEmail.html",context); // context hien tai dang luu gia tri linkConfirm cua map
        helper.setText(html, true);
        mailSender.send(mimeMessage);
        log.info("Mail sent! , emailTo = {}" , emailTo);
    }
}
