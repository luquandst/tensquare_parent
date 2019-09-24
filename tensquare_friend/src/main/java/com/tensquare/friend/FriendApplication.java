package com.tensquare.friend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;
import utils.JwtUtil;

@SpringBootApplication
@EnableDiscoveryClient
public class FriendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendApplication.class);
    }

    //引入idworker
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
    //引入JwtUtil
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
