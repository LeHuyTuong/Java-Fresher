package com.spring.hello.repository;

import com.spring.hello.entity.user.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// cach 2 dung annotation

//@RepositoryDefinition(domainClass = UserEntity.class,idClass = Long.class)
public interface UserRepository  extends JpaRepository<UserEntity, Long> , JpaSpecificationExecutor<UserEntity> { //cach 1 la extend jpa de su dung jpa , <entity , kieu du lieuj gi >

    //user pageabble
    Page<UserEntity> findByUserName(String username,Pageable pageable);

    Page<UserEntity> findByUserNameContaining(String userName, Pageable pageable);
    //find username vs userEmail
    // thuoc tinh nay phai duoc cung voi thuoc tinh duoc khai bao
    // findbyUserNameAndUserEmail
    // UserNameAndUserEmail khi check se chuyen chu cai dau viet hoa thanh viet thuong
    // userNameAndUserEmail - check xem entity co thuoc tinh userName ko
    // userNameAnduserEmail - check xem entity co thuoc tinh userEmail ko
    // where userName = ?1 and userEmail = ?1

    UserEntity findByUserNameAndUserEmail(String userName,String userEmail);
        // Derived Query (Method name-based)
    //userName
    UserEntity findByUserName(String userName);

    /**
     *  Where userName LIKE %?
     */
    List<UserEntity> findByUserNameStartingWith(String userEmail);

    /**
     *  Where userName LIKE ?%
     */
    List<UserEntity> findByUserNameEndsWith(String userEmail);

    /**
     *  Where id < 1
     */
    List<UserEntity> findByIdLessThan(Long id);

    //RAW JPQL
    @Query("Select u FROM UserEntity u where u.id = (select max(p.id) from UserEntity p)")
    UserEntity findMaxId();

    @Query("Select u FROM UserEntity u where u.userName = ?1 AND u.userEmail = ?2")
    List<UserEntity> getUserEntityBy(String userName, String userEmail);

    @Query("Select u FROM UserEntity u where u.userName = :userName AND u.userEmail = :userEmail")
    List<UserEntity> getUserEntityByTwo(@Param("userName") String userName, @Param("userEmail") String userEmail);

    /**
     * Update Delete
     */
    @Modifying
    @Query("UPDATE UserEntity u SET u.userEmail = :userName")
    @Transactional
    int updateUserEmail(@Param("userName") String userName);

    // native query
    /**
     * Get count user use native query
     */
    @Query(value = "SELECT COUNT(id) FROM java_user_001  ", nativeQuery = true)
    long getTotalUsers();
}
