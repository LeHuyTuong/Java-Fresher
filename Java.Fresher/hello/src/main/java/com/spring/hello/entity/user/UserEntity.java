package com.spring.hello.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@Table(name = "java_user_001")
@DynamicInsert
@DynamicUpdate
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // BẮT BUỘC dùng IDENTITY cho MSSQL
    private Long id;

    @JsonProperty("name")
    @Column(columnDefinition = "varchar(255) comment 'user name'", nullable = false)
    private String userName;

    @JsonProperty("email")
    @Column(columnDefinition = "varchar(255) comment 'user email'", nullable = false, unique = true)
    private String userEmail;
}
