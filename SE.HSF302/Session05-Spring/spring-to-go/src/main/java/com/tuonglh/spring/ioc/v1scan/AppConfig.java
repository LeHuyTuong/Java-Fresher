package com.tuonglh.spring.ioc.v1scan;


//Class này là nơi ta khai báo các object dependency của riêng ta
//và gửi cho Spring Container nó giữ
//Cũng là nơi khai báo các thông tin về các dependency khác mà ta ko tự tạo, IoC hẳn cho Spring lo gium
// lưu giữ thông tin về các dependency mà ta nhờ spring quản lí giùm
// object Depen new PdfGenerator , new ContractService , new Repository

// Obj depen sau nay goi la bean

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // @SpringBootApplication khi chơi Spring Boot
@ComponentScan("com.tuonglh.spring.ioc.v1scan")
//scan tất cả cái package nào, coi class nào có @Component, @Service, @Repository, @Controller, @RestController ...
//thì new chúng nó - gọi chúng là bean
public class AppConfig {
}
