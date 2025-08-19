package com.spring.hello.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.hello.entity.feed.FeedEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch =  FetchType.LAZY)
    @ToString.Exclude
    private List<FeedEntity> feedList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cccd_id")
    private CCCDEntity cccd;

}
