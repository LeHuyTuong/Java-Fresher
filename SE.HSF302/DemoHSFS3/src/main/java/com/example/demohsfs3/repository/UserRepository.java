package com.example.demohsfs3.repository;

import com.example.demohsfs3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    // Nếu khóa chính là "id" (mặc định), đã có sẵn: Optional<User> findById(Integer id);

    // Nếu entity của bạn đặt tên field là "userId" (không phải id), bạn có thể thêm:
    public User findByUsernameAndPassword(String username,String password);
}
