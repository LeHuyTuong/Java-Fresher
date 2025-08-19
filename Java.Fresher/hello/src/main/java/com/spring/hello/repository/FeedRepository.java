package com.spring.hello.repository;

import com.spring.hello.entity.feed.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<FeedEntity, Long> {

}
