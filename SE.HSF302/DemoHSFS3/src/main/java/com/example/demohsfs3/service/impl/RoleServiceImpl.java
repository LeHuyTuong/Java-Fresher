package com.example.demohsfs3.service.impl;

import com.example.demohsfs3.dto.CreateRoleRequestDTO;
import com.example.demohsfs3.entity.Role;
import com.example.demohsfs3.repository.RoleRepository;
import com.example.demohsfs3.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public boolean addRole(Role role) {
        return roleRepository.save(role) != null;
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.deleteById(role.getRoleId());
    }

    @Override
    public boolean updateRole(Role role) {
        return roleRepository.save(role) != null;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleById(int id) {
        return roleRepository.findById(id).orElse(null);
    }
}
