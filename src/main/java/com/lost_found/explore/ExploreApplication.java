package com.lost_found.explore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.MultipartConfigElement;

@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication
@ComponentScan(basePackages = "com.lost_found")
@MapperScan(basePackages = {"com.lost_found.dao"})
public class ExploreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExploreApplication.class, args);
    }

}
