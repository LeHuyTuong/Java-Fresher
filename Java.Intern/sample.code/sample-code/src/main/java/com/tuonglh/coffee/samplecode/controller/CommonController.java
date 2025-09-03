package com.tuonglh.coffee.samplecode.controller;

import com.tuonglh.coffee.samplecode.dto.response.ResponseData;
import com.tuonglh.coffee.samplecode.dto.response.ResponseError;
import com.tuonglh.coffee.samplecode.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/common")
@RequiredArgsConstructor
@Slf4j
public class CommonController {
    private final MailSender mailSender;
    private final MailService mailService;

    @PostMapping("/send-email")
    public ResponseData<String> sendEmail(@RequestParam String recipients,
                                          @RequestParam String subject,
                                          @RequestParam String content,
                                          @RequestParam(required = false) MultipartFile[] files) {
        try{
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), mailService.sendMail(recipients, subject, content, files));
        }catch(Exception e){
            log.error("Sending mail failed! , error = {}", e.getMessage());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Sending mail failed!");
        }

    }

}
