package com.tuonglh.coffee.coffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//Depen obj là những class có @Component, @Service, @Repository, @Controller, @RestController
//@SpringBootApplication goojp 3 cái @Configuration @ComponentScan, @EnableAutoConfiguration :
// Khởi động tomcat , JpA/ Hirbernate, MVC- điều khiển request/reponse
public class CoffeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeApplication.class, args);
    }

}
