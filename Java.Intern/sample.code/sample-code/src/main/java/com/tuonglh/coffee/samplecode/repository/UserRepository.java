package com.tuonglh.coffee.samplecode.repository;

import com.tuonglh.coffee.samplecode.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // persistence layer
public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByUsername(String username); // find user by username
        Optional<User> findByEmail(String email); // Optional để tránh lỗi null pointer exception if else cho dễ

        Page<User> findById(Long id, Pageable pageable);

        @Query(value = "select r.name from Role r inner join UserHasRole ur on r.id = ur.user.id where ur.id= :userId")
        List<String> findAllRolesByUserId(Long userId);
}
