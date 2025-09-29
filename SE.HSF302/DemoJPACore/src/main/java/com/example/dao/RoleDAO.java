package com.example.dao;

import com.example.model.RoleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.management.relation.Role;

public class RoleDAO {
    private EntityManager em;
    private EntityManagerFactory emf;

    public RoleDAO(String jpaName) {
        emf = Persistence.createEntityManagerFactory(jpaName);
        em = emf.createEntityManager();
    }

    public boolean addRole(RoleEntity role) {
        try {
            em.getTransaction().begin();
            em.persist(role);
            em.getTransaction().commit();
            return true;
        }catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    public RoleEntity getRoleById(int roleId) {
        return em.find(RoleEntity.class, roleId);
    }


}
