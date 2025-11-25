package com.example.demohsfs3.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @NotBlank
    @NotEmpty
    @Size(min = 4, max = 20, message = "User must be between 4 and 20 characters ")
    @Column(name = "username" , length =  50 , unique = true, nullable = false)
    private String username;

    @Column(name = "password", length =  50 , nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name ="role_id", nullable=false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

}
