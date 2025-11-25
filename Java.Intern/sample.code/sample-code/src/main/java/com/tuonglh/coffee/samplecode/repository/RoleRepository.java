package com.tuonglh.coffee.samplecode.repository;

import com.tuonglh.coffee.samplecode.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query(value = "select r from Role r inner join UserHasRole ur on r.id = ur.user.id where ur.user.id=:userId")
    List<Role> getAllByUserId(Long userId);
}
