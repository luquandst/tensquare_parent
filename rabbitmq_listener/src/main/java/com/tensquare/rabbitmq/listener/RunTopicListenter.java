package com.tensquare.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//接收Topic发送过来的消息
@Component
@RabbitListener(queues = "runwith")
public class RunTopicListenter {
    //接收消息
    @RabbitHandler
    public void getMessage(String msg){
        System.out.println("runwith接收消息："+msg);
    }
}
