package com.tuonglh.superapp.entity.unione;

import com.tuonglh.superapp.infra.JpaUtil;
import jakarta.persistence.EntityManager;

public class MainUniOne {

    //psvm tab để có hàm main(), có nút play để run
//NHO VA JPAUTIL, CO HÀM DOC FILE PERSISTENCE. XML DE KET NOI DUNG CSDL,
    //ĐÚNG DRIVER, VÀ GIÚP TẠO RA ÔNG QUẢN LÍ ENTITY ENTITY-MANAGER
    public static void main(String[] args) {
    createMajorStudents();
    }

    public static void createMajorStudents(){
        // ta tạo chuyên ngành SE và 2 sinh viên của chuyên ngành này
        Major seMajor = new Major("SE" , "SOFTWARE ENGINEERING");
        Student s1 = new  Student("SE1" , "An Nguyen", 2006, 8.6);
        Student s2 = new  Student("SE2" , "Huy Tuong", 2005, 8.9);
        // hiện chưa có quan hệ j với 2 thằng major vavf student

        // seMajor cần phải add 2 Student s1 s2 vào cái List students
        // làm sao add
        // nhưng mà add như này thì public mà public thì lộ hết à , tự làm hàm set cho list
        //seMajor.students.add(s1);
        seMajor.addStudent(s1);
        seMajor.addStudent(s2);
        // OOP đã xong về relationship
        // xuống table, đổ domino, cascade 1 major, N student xuống luôn theo one di xuống, many đi theo

        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();

        em.persist(seMajor);

        em.getTransaction().commit();

        em.close();

    }
}
