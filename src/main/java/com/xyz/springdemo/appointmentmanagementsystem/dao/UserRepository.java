package com.xyz.springdemo.appointmentmanagementsystem.dao;

import com.xyz.springdemo.appointmentmanagementsystem.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String s);
    @Override
    Optional<User> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Modifying
    @Query(value = "delete from users where username = ?1 ",nativeQuery = true)
    void deleteByUsername(String username);

    @Modifying
    @Query(value = "update users u set u.username = ?1, u.password = ?2 where u.id = ?3",nativeQuery = true)
    void setUserInfoById(String username, String password, Integer userId);

}
