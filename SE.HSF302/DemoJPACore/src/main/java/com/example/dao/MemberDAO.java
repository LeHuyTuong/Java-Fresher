package com.example.dao;

import com.example.model.MemberEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.lang.reflect.Member;

public class MemberDAO {
    private EntityManager em;
    private EntityManagerFactory emf;

    public MemberDAO(String jpaName){
        emf = Persistence.createEntityManagerFactory(jpaName);
        em = emf.createEntityManager();
    }

    public boolean addMember(MemberEntity member) {  // thay đổi DB phải có Transaction
        try {
            em.getTransaction().begin();
            em.persist(member);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateMember(MemberEntity updatedMember) {
        try {
            MemberEntity existingMember = em.find(MemberEntity.class, updatedMember.getId());
            if (existingMember == null) {
                return false;
            }

            em.getTransaction().begin();
            existingMember.setName(updatedMember.getName());
            existingMember.setEmail(updatedMember.getEmail());
            existingMember.setPassword(updatedMember.getPassword());
            existingMember.setRole(updatedMember.getRole());
            em.merge(existingMember);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeMember(long memberId) {
        try {
            MemberEntity member = em.find(MemberEntity.class, memberId);
            if(member != null) {
                em.getTransaction().begin();
                em.remove(member);
                em.getTransaction().commit();
                return true;
            }
            return false;
        }catch(Exception e){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public MemberEntity getMemberById(long memberId) {
        return em.find(MemberEntity.class, memberId);
    }
}
