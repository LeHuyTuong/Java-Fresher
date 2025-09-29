package com.example.model;


import jakarta.persistence.*;

@Entity
@Table(name = "members")

public class MemberEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tăng dần ko cần nhập
    private Long id;

    @Column(name = "name" , length = 20, nullable = false)
    private String name;

    @Column(name = "email", length = 20, nullable = false)
    private String email;

    @Column(name = "password", length = 64, nullable = false)
    private String password;


    @ManyToOne
    @JoinColumn(name="role_id")
    private RoleEntity role;

    public MemberEntity() {
    }

    public MemberEntity(Long id, String name, String email, RoleEntity role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public MemberEntity(Long id, String name, String email, String password, RoleEntity role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public MemberEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
