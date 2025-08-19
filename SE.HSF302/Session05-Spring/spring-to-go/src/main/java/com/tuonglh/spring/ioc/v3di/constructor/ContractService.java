package com.tuonglh.spring.ioc.v3di.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

    private final PdfGenerator gen;

    @Autowired
    public ContractService(PdfGenerator gen) {
        this.gen = gen;
    }

    public void processContract() {
        gen.generateFile("lmasdasd");
    }
}
