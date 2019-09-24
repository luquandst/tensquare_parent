package com.tensquare.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "luquanmq")
public class DirectListener {
    //获取直接发送过来的消息
    @RabbitHandler
    public void getQueue(String msg){
        System.out.println("获取直接发送过来的消息："+msg);
    }
}
