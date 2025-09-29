package com.example.model;


import jakarta.persistence.*;

@Entity
@Table(name ="role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id",  nullable = false, updatable = false, unique = true)
    private int roleId;

    @Column(name = "role_name", nullable = false, updatable = false)
    private String roleName;

    public RoleEntity(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public RoleEntity() {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}
