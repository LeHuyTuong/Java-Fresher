package com.example.service;

import com.example.model.RoleEntity;

public interface RoleService {
    public boolean addRole(RoleEntity role);
    public RoleEntity getRoleById(int roleId);
}
