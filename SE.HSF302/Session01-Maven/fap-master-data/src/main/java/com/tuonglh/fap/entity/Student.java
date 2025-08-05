package com.tuonglh.fap.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
@ToString
// thay bang @Data la du
public class Student {
    private String id;
    private String name;
    private int yob;
    private double gpa;
    //ĐOAN CODE BOILER PLATE NHAM CHAN: 2 CTOR, GET/SET, TOSTRING
//TA KO THEM LÀM THEO CÁCH TRUYỀN, NHUNG VAN PHẢI LÀM THEO CÁCH NÀO ĐÓ -> TA DÙNG THÊM 1 THU VIỆN TRÊN MANG GIÚP TA GENERATE
// ĐÁM NÀY 1 CÁCH TU DONG -> LOMBOK DEPENDENCY
//LOMBOK LÀ HÀNG A E MẠNG, KO PHẢI CHÍNH HÃNG JDK
// Ta di tai thu vien LOMBOK qua file POM.XML

}
