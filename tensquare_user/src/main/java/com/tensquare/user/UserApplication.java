package com.tensquare.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import utils.IdWorker;
import utils.JwtUtil;

@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
    //注册idworker
    @Bean
    public IdWorker idWorker(){
        return  new IdWorker(0,1);
    }
    //配置密码加密
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //配置jwt工具类
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
