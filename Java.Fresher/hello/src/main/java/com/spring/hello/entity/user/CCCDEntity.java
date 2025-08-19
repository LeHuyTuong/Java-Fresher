package com.spring.hello.entity.user;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "java_cccd_001")
@Data
public class CCCDEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numberCCCD;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
