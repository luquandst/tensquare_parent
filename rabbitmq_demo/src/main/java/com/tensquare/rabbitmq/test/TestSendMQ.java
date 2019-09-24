package com.tensquare.rabbitmq.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSendMQ {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //直接发送消息
    @Test
    public void sendMessageQueue(){
        rabbitTemplate.convertAndSend("luquanmq","利用rabbitmq直接发送消息");
    }

    //测试消息发送分裂模式
    @Test
    public void sendMessageFanout(){
        rabbitTemplate.convertAndSend("limeng","","正在利用分裂模式发送消息...");
    }

    //测试主题模式发送消息
    @Test
    public void sendMessageTopic1(){
        rabbitTemplate.convertAndSend("status","start.aa","正在发送topic模式的消息");
    }

    //测试主题模式发送消息
    @Test
    public void sendMessageTopic2(){
        rabbitTemplate.convertAndSend("status","222.end","正在发送topic模式的消息");
    }

    //测试主题模式发送消息
    @Test
    public void sendMessageTopic3(){
        rabbitTemplate.convertAndSend("status","start.end","正在发送topic模式的消息");
    }

}
