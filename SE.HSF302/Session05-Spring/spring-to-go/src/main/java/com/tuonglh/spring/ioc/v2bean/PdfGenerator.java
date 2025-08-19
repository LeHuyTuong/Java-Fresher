package com.tuonglh.spring.ioc.v2bean;


import org.springframework.stereotype.Component;

//@Component // de lam BEAN , tu new boi SPring IOC Container
public class PdfGenerator {

    public void generateFile(String fileName) {
        // ToDo: logic xu ly gen ra file pdf .

        System.out.println("The pdf file" + fileName + ".pdf has been generated successfully.");
    }
}
