package com.tensquare.rabbitmq.listener;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.SmsUtil;

import java.util.HashMap;
import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @Autowired
    private SmsUtil smsUtil;

    //接收消息并发送短信
    @RabbitHandler
    public void sendMsg(Map map){
        try {
            System.out.println("正在接收消息");
            //获取手机号
            String mobile = (String) map.get("mobile");
            System.out.println("mobile = " + mobile);
            //获取验证码
            String verifyCode = (String) map.get("verifyCode");
            System.out.println("verifyCode = " + verifyCode);
            //定义一个新的集合组织参数
            Map param = new HashMap();
            //将参数放到集合中去
            param.put("code",verifyCode);
            //将参数转化为字符串
            String params = JSON.toJSONString(param);
            //发送短信
            smsUtil.sendSms(mobile,params);
            System.out.println("发送短信消息成功");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
