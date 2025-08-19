package com.tuonglh.spring.noioc;

public class MainNoIoC {
    //nơi tạo ra object, tạo ra dependency,
    //IoC container - Inversion of Control container
    //em kiểm soát những đứa phụ thuộc, em chích tiêm chúng vào cho anh tiệm chích vào trong object chính
    //IoC handmade . ko phai cua spring

    public static void main(String[] args) {
        PdfGenerator gen = new PdfGenerator();
        ContractService service = new ContractService(gen);
        service.processContract();

    }

}
