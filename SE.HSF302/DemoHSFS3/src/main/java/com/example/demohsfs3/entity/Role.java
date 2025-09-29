package com.example.demohsfs3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_name" , length = 20 , unique = true, nullable = false)
    private String roleName;


    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>(); // user ko trung nen la set
}
