package com.example.springwebdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// 扫描Servlet注解
@ServletComponentScan
// 启用jpa和jpa的repository
@EnableJpaRepositories(basePackages = "com.example.springwebdemo.jpa")
@EntityScan(basePackages = "com.example.springwebdemo.entity")
public class SpringwebDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringwebDemoApplication.class, args);
    }

}
