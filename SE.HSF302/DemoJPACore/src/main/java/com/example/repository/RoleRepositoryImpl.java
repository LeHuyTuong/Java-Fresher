package com.example.repository;

import com.example.dao.RoleDAO;
import com.example.model.RoleEntity;

public class RoleRepositoryImpl implements RoleRepository {

    private RoleDAO roleDAO ;

    public RoleRepositoryImpl(String jpaName) {
        roleDAO = new RoleDAO(jpaName);
    }

    @Override
    public boolean addRole(RoleEntity role) {
        return roleDAO.addRole(role);
    }

    @Override
    public RoleEntity getRoleById(int roleId) {
         return roleDAO.getRoleById(roleId);
    }
}
