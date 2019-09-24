package com.tensquare.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utils.SmsUtil;

@SpringBootApplication
public class ListenerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ListenerApplication.class);
    }

    //注入发送短信工具
    @Bean
    public SmsUtil smsUtil(){
        return new SmsUtil();
    }
}
