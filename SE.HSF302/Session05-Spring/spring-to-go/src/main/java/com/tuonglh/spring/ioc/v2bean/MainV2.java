package com.tuonglh.spring.ioc.v2bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainV2 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(PdfGenerator.class);
        PdfGenerator gen2 = (PdfGenerator) context.getBean("ngoctrinh");
        gen2.generateFile("hehe");
    }
}
