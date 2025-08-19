package com.tuonglh.spring.ioc.v3di.constructor;

import org.springframework.stereotype.Component;

@Component
public class PdfGenerator {
    public void generateFile(String fileName) {
        System.out.println("V3 DI IOC MLEM -> The pdf file " + fileName + ".pdf has been generated successfully.");
    }
}
