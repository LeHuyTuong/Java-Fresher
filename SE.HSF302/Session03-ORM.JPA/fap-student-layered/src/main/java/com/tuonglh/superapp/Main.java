package com.tuonglh.superapp;

import com.tuonglh.superapp.entity.Student;
import com.tuonglh.superapp.service.StudentService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //FLOW: UI (here)--- SERVICE --- REPO --- JPAUTIL  (ENTITY-MANAGER VÀ  FACTORY) -- Table
        StudentService studentService = new StudentService();

        //1. TẠO MỚI HỒ SƠ SINH VIÊN
        //Student an = new Student("SE10" , "An Nguyen", 2005, 8.6);
        //studentService.createStudent(an);
        //studentService.createStudent(new Student("SE08", "Huy Tuong", 2005, 9.6));
        //2. SHOW ALL
        System.out.println("The list of students has been created");
        studentService.getAllStudents().forEach( x -> System.out.println(x));


        //3. UPDATE 1 HỒ SƠ SV
        Student an = new Student("SE10", "An Nguyen", 2005, 9.2);
        studentService.updateStudent(an);
        System.out.println("The list of students has been updated");
        studentService.getAllStudents().forEach( x -> System.out.println(x));
        //4. REMOVE 1 Hồ SƠ SV
        studentService.deleteStudent("SE08");
        System.out.println("The list of students has been deleted");
        studentService.getAllStudents().forEach( x -> System.out.println(x));
    }
}