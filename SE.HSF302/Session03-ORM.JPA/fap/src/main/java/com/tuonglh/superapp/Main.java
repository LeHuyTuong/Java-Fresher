package com.tuonglh.superapp;

import com.tuonglh.superapp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.tuonglh.superapp-PU");

        insertStudents(emf);
        getAllStudents(emf);

        emf.close(); // đóng sau khi dùng xong
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
