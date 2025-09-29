package com.example.repository;

import com.example.model.RoleEntity;

public interface RoleRepository {

    public boolean addRole(RoleEntity role);
    public RoleEntity getRoleById(int roleId);
}
