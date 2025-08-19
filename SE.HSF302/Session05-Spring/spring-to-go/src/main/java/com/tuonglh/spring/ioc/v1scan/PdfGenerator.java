package com.tuonglh.spring.ioc.v1scan;


import org.springframework.stereotype.Component;

@Component //@Component nghĩa là: đây là 1 obj, 1 bean, sẽ đc tự động new bởi
//thư viện Spring, Spring Context no usages
//@Service, @Repository cx đc luôn, 2 thằng này là con của @Component
public class PdfGenerator {

    public void generateFile(String fileName) {
        // ToDo: logic xu ly gen ra file pdf .

        System.out.println("The pdf file" + fileName + ".pdf has been generated successfully.");
    }
}
