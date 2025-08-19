package com.tuonglh.spring.noioc;

public class PdfGenerator {
//class này chuyên lo việc generate ra pdf - thoa SRP trong SOLID
//Làm giả cái hàm generateFile() ko làm thật vì tốn thời gian, ngoài scope
//giả nhưng chạy đc, gọi là Mock
    public void generateFile(String fileName) {
        // ToDo: logic xu ly gen ra file pdf .

        System.out.println("The pdf file" + fileName + ".pdf has been generated successfully.");
    }
}
