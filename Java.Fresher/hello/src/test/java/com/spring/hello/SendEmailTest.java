package com.spring.hello;

import com.spring.hello.util.EmailSenderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@SpringBootTest
public class SendEmailTest {
    @Autowired
    private EmailSenderUtil emailSenderUtil;

    @Test
    void sendTextEmail(){
        String to = "dihoc77@gmail.com";
        String subject = "Test OTP Simple";
        String content = "This is a test 1234";
        emailSenderUtil.sendTextEmail(to,subject,content);
    }


    @Test
    void sendHtmlEmail() throws IOException {
        // Địa chỉ email người nhận
        String to = "dihoc77@gmail.com";
        // Tiêu đề email
        String subject = "Test OTP HTML Simple";
        // Nội dung text ngắn gọn (ở đây không dùng vì ta sẽ gửi HTML)
        String content = "OTP is 123";
        // Lấy file HTML trong folder resources/templates/ email/otp-auth.html
        // ClassPathResource: dùng để load file từ thư mục resources
        Resource resource = new ClassPathResource("/templates/ email/otp-auth.html");
        // Đọc toàn bộ nội dung file HTML thành String
        // resource.getInputStream() → đọc file
        // readAllBytes() → đọc hết thành mảng byte
        // new String(...) → chuyển byte[] thành String (chính là HTML content)
        String htmlContent = new String(resource.getInputStream().readAllBytes());
        // Gửi email bằng util (hàm đã viết sẵn)
        // Tham số: email người nhận, subject, nội dung HTML
        emailSenderUtil.sendHtmlEmail(to, subject, htmlContent);
    }


}
