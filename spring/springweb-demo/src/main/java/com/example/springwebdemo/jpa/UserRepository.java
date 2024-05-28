package com.example.springwebdemo.jpa;

import com.example.springwebdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhengzz
 * @version 1.0.0
 * @description TODO
 * @date 2024/5/16
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
