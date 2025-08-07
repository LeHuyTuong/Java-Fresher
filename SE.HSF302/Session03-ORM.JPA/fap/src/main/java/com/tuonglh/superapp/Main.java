package com.tuonglh.superapp;

import com.tuonglh.superapp.entity.Lecturer;
import com.tuonglh.superapp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static EntityManagerFactory emf  = Persistence.createEntityManagerFactory("com.tuonglh.superapp-PU");
    public static void main(String[] args) {
        //insertStudents(emf);
        //getAllStudents(emf);
        //insertLectures(emf);
        getAllLectures(emf);
        emf.close(); // đóng sau khi dùng xong
    }

    public static void insertLectures(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        Lecturer  an = new Lecturer("An Nguyen" , 1990, 20_000_000);
        Lecturer  tuong = new Lecturer("Tuong Le" , 1990, 20_000_000);
        //VÌ CÓ THAY ĐỒI TRÊN CSDL (TABLE, DATA) NÊN TA CẦN THEO DÕI CHẶT CHẾ CÁC
        //    CÂU LENH -> DÙNG KHÁI NIEM TRANSACTION: DO ALL OR NOTHING
        //HOẶC TẤT CẢ, HOẶC KO GÌ CẢ. NGUYÊN LÝ ACID CỦA TRANSACTION
        //VÍ DỤ: BẠN CHUYỂN TIỀN TRẢ NỢ THẰNG BẠN 1 TRIỆU ĐỒNG
        //TỪ TPBANK (MÌNH) SANG ACB (THẰNG BẠN)
        // => CẢ 2 PHẢI XẢY RA MỚI OKIE
        //ACB +1M
        //RỚT 1 TRONG 2, ROLLBACK, HOẶC TẤT CẢ, HOẶC CHƯA GÌ CẢ !!!
        //BÊN TPBANK: KHỞI DONG NGAY TRANSACTION THEO DÕi
        //MÌNH ĐÃ -1M, MÃI CHUA THẤY BÊN KIA NOTI OKIE -> NHÀ MINH UNDO !!!
        //TPBANK -1M VÀ
        em.getTransaction().begin();
        em.persist(an);
        em.persist(tuong);
        em.getTransaction().commit();
        em.close();
    }



    //WHERE
    //SQL: SELECT * FROM Lecturer WHERE Salary = 2000000 //tên cột trong table
    //JPQL: SELECT x FROM Lecturer x WHERE x. salary = 2000000 //tên field trong class
    // x. là biến object nhen !!! , ko phải cot trong talbe !!!
    public static void getAllLectures(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        List<Lecturer> list = em.createQuery("Select x from Lecturer x Where x.salary > 2000", Lecturer.class).getResultList();
//        System.out.println("The List of Lecturer:");
//        for (Lecturer x : list) {
//            System.out.println(x);
//        }

        //BIỂU THỨC LAMBDA - LAMBDA EXPRESSION, DÍNH DÁNG CỰC KÌ CHẶT CHẾ VỚI
        //STREAM-API, CƠ CHẾ XỬ LÍ NHIỀU DỮ LIỆU Ở TRONG RAM
        //LAMBDA EXPRESSION LÀ HÀM VÔ DANH, HÀM ẨN DANH !!! HÀM KO CÓ TÊN
        //DÍNH ĐẾN KHÁI NIỆM LẬP TRÌNH HÀM - FUNCTIONAL PROGRAMMING
        //HÀM ĐC XEM LÀ 1 DATA, VÀ HÀM LÀ THAM SỐ ĐỂ TRUYỀN VÀO HÀM KHÁC

        System.out.println("The list of students printed by lambda expression");
        list.forEach(x -> {
            System.out.println(x);
        });

        // Học Thêm VỀ JPQL : JAVA Persistence query language
        // là phiên bản độ từ SQL nhưng dành cho thế giới OOP. Object
        // Hibernate cx có phiên bản riêng nữa của nó gọi HQL
        // SQL : SELECT * FROM Lecturer
        // JPQL : FROM LECTURER
        //       Select lec from lecturer lec
        //                  Với mỗi dòng / record lấy từ table lecturer
        //                  ta new nó trong RAM , new Lecturer() và gọi vùng new này là
        //                  lec , tức là lec = new Lecturer()
        //                   và lặp lại toàn bộ record trong table Lecture Add kq đọc từ talbe vào kết quả cuối cùng dùng lệnh select lec
        //                   tức là lấy từng object lec được new từ từng dòng trong



        em.close();
    }
    public static void insertStudents(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        Student an = new Student("SE1", "AnNguyen", 2005, 8.8);
        Student tuong = new Student("SE2", "TuongNgu", 2005, 9.2);

        em.getTransaction().begin();
        em.persist(an);
        em.persist(tuong);
        em.getTransaction().commit();

        em.close();
    }

    public static void getAllStudents(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        List<Student> result = em.createQuery("FROM Student", Student.class).getResultList();
        System.out.println("The List of students:");
        for (Student x : result) {
            System.out.println(x);
        }

        em.close();
    }
}
