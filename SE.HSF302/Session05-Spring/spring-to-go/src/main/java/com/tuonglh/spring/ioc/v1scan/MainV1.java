package com.tuonglh.spring.ioc.v1scan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainV1 {

    public static void main(String[] args) {
        // khoi tao contain , chua object , bean vao spring
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        //context thuoc class ApplicationContext là TRÙM CUỐI quản lí các
        //     object - bean, quản lí luôn việc tiêm vào các object khác
        //ĐỨA NÀO MUỐN ĐƯỢC TIÊM VÀO, ĐỨA ĐÓ CX PHẢI LÀ BEAN
        //TRÙM CUỐI CONTEXT - CHƠI TRONG RAM
        //TRÙM CUỐI NÀY GIONG ENTITY-MANAGER-FACTORY ĐÃ HỌC - CHƠI DB

        //Lay beam ra xai
        PdfGenerator pdfGenerator = (PdfGenerator) context.getBean("pdfGenerator");
        pdfGenerator.generateFile("122154");

        PdfGenerator pdfGenerator2 = (PdfGenerator) context.getBean("pdfGenerator", PdfGenerator.class); // ep kieu
        pdfGenerator2.generateFile("lmao");

        PdfGenerator pdfGenerator3 = (PdfGenerator) context.getBean( PdfGenerator.class); // lay ten mac dinh
        pdfGenerator3.generateFile("Gen3");
    }
}
