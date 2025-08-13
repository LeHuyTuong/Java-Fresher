package com.tuonglh.superapp.entity.unimany;

import com.tuonglh.superapp.infra.JpaUtil;
import jakarta.persistence.EntityManager;

public class MainUniMany {

    public static void main(String[] args) {
        createMajorStudents();
    }

    public static void createMajorStudents(){
        Major se = new Major("SE", "SOFTWARE E");
        Student an = new Student("SE1", "AN Cute", 2005, 8.6);
        Student tuong = new Student("SE2", "tuong Cute", 2005, 8.8);

        an.setMajor(se);
        tuong.setMajor(se);
        //XUỐNG DB, PHẢI NHỜ JPAUTIL, CẦN CÓ NH XƯỞNG FACTORY (KẾT NỐI SQL
        //SERVER), MUON ÔNG GIAM ĐOC GIAM SÁT CÁC ENTITY ENTITY MANAGER, CHUYEN XỬ
        //LÍ ĐÁM @ENTITY
        //THÊM XOÁ SỬA DB THÌ PHẢI NHỚ CÓ TRANSACTION
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(se);
        em.persist(an);
        em.persist(tuong);
        em.getTransaction().commit();
        em.close();
    }
}
