package com.tensquare.user.service;

import com.tensquare.user.dao.UserDao;
import com.tensquare.user.pojo.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    //发送短信
    public void sendsms(String mobile) {
        //生成一个的16位的随机码
        String code = idWorker.nextId()+"";
        //生成六位的验证码
        String verifyCode = code.substring(0, 6);
        System.out.println("verifyCode = " + verifyCode);
        //构造要发送消息的内容
        HashMap<Object, Object> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("verifyCode",verifyCode);
        //把验证码保存到redis中去
        redisTemplate.opsForValue().set(mobile,verifyCode,5,TimeUnit.MINUTES);
        //发送消息
        rabbitTemplate.convertAndSend("sms",map);
    }

    //用户注册
    public void register(String code, User user) {
        //从redis中取出验证码
        String verifyCode = (String) redisTemplate.opsForValue().get(user.getMobile());
        //判断验证码和用户输入的是否一致
        if (verifyCode != null){
            //如果一致的话就把用户保存到数据库中
            if (verifyCode.equals(code)){
                //设置用户的id
                user.setId(idWorker.nextId()+"");
                //将用户的密码进行加密
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                //保存用户
                userDao.save(user);
            }
        }
    }

    //用户登录，手机号和密码都正确就返回当前用户，否则返回空值
    public User login(User user) {
        //根据用户的手机号获取从数据库查询数据
        User loginUser = userDao.findByMobile(user.getMobile());
        //判断查询到用户是否为空以及密码是否一致
        if (loginUser != null && passwordEncoder.matches(user.getPassword(),loginUser.getPassword())){
            //返回当前的用户
            return loginUser;
        }
        //返回空
        return null;
    }

    //根据用户删除用户
    public void delete(String userid) {
        userDao.deleteById(userid);
    }
}
