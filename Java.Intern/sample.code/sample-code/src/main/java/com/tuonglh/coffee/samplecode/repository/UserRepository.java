package com.tuonglh.coffee.samplecode.repository;

import com.tuonglh.coffee.samplecode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // persistence layer
public interface UserRepository extends JpaRepository<User, Long> {

}
