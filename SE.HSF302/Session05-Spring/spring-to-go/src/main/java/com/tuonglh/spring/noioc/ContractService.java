package com.tuonglh.spring.noioc;

public class ContractService {
    //code chính của minh, và minh lo CRUD table Contract qua Repo
    //sau đo minh se generate pdf. Nho PdfGenerator giup, vay PdfGenerator la
    //Dependency và tự new ở đây, hoặc phải đc truyền vào - chích vào tiêm vào

    private PdfGenerator gen;

    // chich depen vao constructor
    public ContractService(PdfGenerator gen) {
        this.gen = gen;
    }

    public void processContract(){
        // ToDo logic xu li hop dong

        //gen pdf
        gen.generateFile("412312412");
    }

    // chich qua setset
    public void setPdf(PdfGenerator gen) {
        this.gen = gen;
    }

}
