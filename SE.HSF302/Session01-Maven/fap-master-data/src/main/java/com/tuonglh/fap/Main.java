package com.tuonglh.fap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuonglh.fap.entity.*;

import java.lang.runtime.ObjectMethods;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        int yob = 2004;
        Subject swt = new Subject ( "SWT301", "SOFWARE TESTING",  3, 45) ;
        Subject hsf = new Subject ( "HSF302", "HIBERNATE SPRING FRAMEWORK",  3, 45) ;
//SHOW INFO 2 MÔN
        //System.out.println("SWT INFO: "+ swt.toString()) ;
        //System.out.println ("HSF INFO: " + hsf); //gọi thầm tên em
        Student an = new Student("SE1", "An" , yob, 86);
        System.out.println(an.toString());

        // choi vs json , tu object thanh json va nguoc lai
        //can tao oobject quan ly json tu thu vien jackson
        ObjectMapper mapper = new ObjectMapper();
        String anJson = mapper.writeValueAsString(an);
        System.out.println(anJson);

       // String tuongJson = "{\"id\":\"SE1\",\"name\":\"Tuong\",\"yob\":2004,\"gpa\":86.0}";
        String tuongJson = """
                {"id":"SE1","name":"An","yob":2004,"gpa":86.0}"""; // JDK15 , RAWSTRINg Co sao luu vay , co ki tu DB giu nguyen
        Student tuong = mapper.readValue(tuongJson, Student.class); // chuoi va convert thanh object nao thuoc class nao
        System.out.println(tuong.toString());
    }
}