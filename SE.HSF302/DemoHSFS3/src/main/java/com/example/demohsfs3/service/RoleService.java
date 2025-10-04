package com.example.demohsfs3.service;

import com.example.demohsfs3.dto.CreateRoleRequestDTO;
import com.example.demohsfs3.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    public Role findByRoleName(String roleName);
    public boolean addRole(Role role);
    public void deleteRole(Role role);
    public boolean updateRole(Role role);
    public List<Role> findAll();
    public Role findRoleById(int id);
}
