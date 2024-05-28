package com.example.springwebdemo.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
@SpringBootTest
class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;
    @Test
    public  void  query(){
        userRepository.findAll();
    }

}