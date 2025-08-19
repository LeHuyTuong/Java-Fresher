package com.spring.hello.entity.feed;


import com.spring.hello.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "java_feed_001")
public class FeedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedId", nullable = false)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Lob // ánh xạ văn bản dài
    @Column(nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id", nullable = false) // khóa ngoại foreign key FeedUntity
    private UserEntity user;

}
