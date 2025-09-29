package com.example.service;

import com.example.dao.RoleDAO;
import com.example.model.RoleEntity;
import com.example.repository.RoleRepository;
import com.example.repository.RoleRepositoryImpl;

public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;


    public RoleServiceImpl(String jpaName) {
        roleRepository = new RoleRepositoryImpl(jpaName);
    }


    @Override
    public boolean addRole(RoleEntity role) {
        return  roleRepository.addRole(role) ;
    }

    @Override
    public RoleEntity getRoleById(int roleId) {
        return roleRepository.getRoleById(roleId);
    }
}
