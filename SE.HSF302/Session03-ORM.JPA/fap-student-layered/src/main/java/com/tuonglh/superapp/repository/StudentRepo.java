package com.tuonglh.superapp.repository;

import com.tuonglh.superapp.entity.Student;
import com.tuonglh.superapp.infra.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class StudentRepo {
    //CLASS NAY CHUA CAC HAM CRUD TRUC TIEP TABLE STUDENT - REPO: NHA KHO VỀ DU LIEU
    //MUON CRUD TABLE THI PHẢI NHO VA ONG ENTITY-MANAGER DC CUNG CAP TU JpaUtil SINGLETON
    //FLOW: UI --- SERVICE --- REPO (DÂY) --- JPAUTIL (ENTITY-MANAGER VÀ FACTORY) --- TABLE


    //CÁC HÀM CRUD Ở DAY THUONG DAT TEN NGAN GON, HUONG HANH DONG GIONG NHU LENH SQL CHUAN (INSERT, UPDATE, DELETE)
    //TÊN HÀM GOI Y: save() update() delete () remove () find() findAll() findById()
    //TUY HÀM, NHUNG NEU CO THAY DOI TRONG TABLE (INSERT, UPDATE, DELETE) THI
    //HÀM SẾ NHẬN VÀO OBJECT (INSERT, UPDATE), HOAC KEY (DELETE) DELETE ĐUA OBJECT VẪN ĐC
    //VÌ VÀO TRONG OBJECT, GET FIELD KEY ĐỂ XOÁ
    //NHO DÙNG TRANSACTION KHI THAY ĐỔI DATA TRONG TABLE (INSERT, UPDATE, DELETE)
    //SELECT KO CẦN, VI ko thay doi data

    //HÀM INSERT XUỐNG TABLE
    public void save(Student obj){
        // Goi EM nho giup , kem transation, co thay doi data
        EntityManager em = JpaUtil.getEntityManager(); // doan static{} chay duy nhat 1 lan , khoi dong heavy connection
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
        //try catch khi save bi loi - tu tu

        em.close();
    }

    // ham lay tat ca sinh vien - JPQL select s from student s
    public List<Student> findAll(){
        EntityManager em = JpaUtil.getEntityManager();
        return  em.createQuery("From Student " , Student.class).getResultList();
    }

    public void update(Student obj){
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(obj);//merge() nghĩa là tích hợp object đưa vào vào trong EM
        //EM nó se đổ ngang, copy ngang obj vao TRONG obj ứng với dong trong tabl
        //nếu bạn cố tình đưa object mà key ko tồn tại trong table, sẽ insert mới
        em.getTransaction().commit();
        em.close();
    }

    //OverLoad
    public void delete(String id){
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        Student x = em.find(Student.class, id);
        em.remove(x);
        // em.remove(em.find(Student.class, id)); viet gop
        em.getTransaction().commit();
        em.close();
    }

    // Ham where theo cot nao do , findById() tra ve 1 student
    public Student findById(String id){
        EntityManager em = JpaUtil.getEntityManager();
        return em.find(Student.class, id);
    }

}
