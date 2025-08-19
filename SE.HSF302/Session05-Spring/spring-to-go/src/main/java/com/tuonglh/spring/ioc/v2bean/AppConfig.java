package com.tuonglh.spring.ioc.v2bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // bao appcontext de new bean , controller , inject
//@ComponentScan("com.tuonglh.spring.ioc.v2bean")
public class AppConfig {

    // new bean o day
    @Bean("ngoctrinh") // tự truyền param vào trong bean
    // obj duoc return , di vao container
    // chu dong new , chu dong dat ten obj , dua obj vao container
    // ten ham chuan Verb + Obbject mang y nghia ten bien obj , được dùng trong container

    public PdfGenerator pdfGenerator() {
        return new PdfGenerator();   // ten ham bang gia tri return , PdfGenerator getPdfGenerator = new PdfGenerator();
    }

//    @Autowired
//    @Qualifier("pdfGenerator")
//    private DocumentGenerator generator;      chỉ đúng theo tên pdfGen

//    public DocumentGenerator excelGenerator(){
//        return new
//    }
//
//    @Bean
//    public DocumentGenerator excelGenerator(){
//        return new ExcelGenerator();
//    }

    //TODO: ĐIỀU GÌ XẢY RA NẾU TA CÓ 2 OBJECT CỦA CÙNG CLASS ???
    //VÍ DỤ: PdfGenerator và ExcelGenerator đều là (implements) interface
    //    DocumentGenerator, xung đột 2 object cùng kiểu Cha
    //@Primary, @Qualifier khi có nhiều bean cùng kiểu


    //TODO Spring sẽ ném lỗi NoUniqueBeanDefinitionException vì nó không biết chọn ExcelGenerator hay PdfGenerator.
}
