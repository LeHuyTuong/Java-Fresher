package com.tuonglh.superapp.entity.bidirectional;

import com.tuonglh.superapp.infra.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MainBiOneMany {
    public static void main(String[] args) {
        //createMajorStudents();
        getALl();
    }

    // lay danh sach sv, ds chuyen nganh
    // ham find tra ve duy nhat 1 dong theo key
    // createQuery ( cau JPQL) tra ve 1 list
    //VÌ OOP 2 CHIỀU, NÊN KHI LẤY ĐC 1 MAJOR SE, KO CẦN WHERE GI NỮA, VÀO THANG LIST
    //CỦA SE LÀ LẤY FULL STUDENT CỦA SE, TA LAY QUA OBJECT MAJOR MÀ LẠI CÓ LIST
    //STUDENT, VÌ MAJOR CÓ NHIỀU SV
    //THAY VI CHOI TRUC TIEP TABLE STUDENT

    public static void getALl(){
        EntityManager em =  JpaUtil.getEntityManager();
        Major se = em.find(Major.class, "SE");
        System.out.println(se);

        List<Student> ls =se.getStudentList();
        System.out.println(ls);
        ls.forEach(nt -> System.out.println(nt));
    }
    public static void createMajorStudents(){
        Major se = new Major("SE" , "SOFTWARE-ENGINEERING");
        Major jd = new  Major("JD" , "JAVA");
        Student an = new Student("SE1312", "An Cute", 2012,7.5);
        Student tuong = new Student("SE13312", "tuong Cute", 34,7.55);

        Student an1 = new Student("GD1312", "An das Cute", 2012,7.5);
        Student tuong1 = new Student("GD123", "tuong das Cute", 34,7.55);

        jd.addStudent(an1);
        jd.addStudent(tuong1);
        se.addStudent(an);
        se.addStudent(tuong);


        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(se);
        em.persist(jd);
        em.getTransaction().commit();
        em.close();
    }
}
