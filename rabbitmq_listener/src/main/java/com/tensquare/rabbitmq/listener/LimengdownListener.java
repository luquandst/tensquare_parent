package com.tensquare.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "limengdown")
public class LimengdownListener {

    //接收消息
    @RabbitHandler
    public void getMessage(String msg){
        System.out.println("limengdown正在接收消息："+msg);
    }
}
