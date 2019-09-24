package com.tensquare.user.controller;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import jdk.net.SocketFlow;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    //发送短信
    @PostMapping("/sendsms/{mobile}")
    public Result sendsms(@PathVariable String mobile){
        //调用service层发送短信
        userService.sendsms(mobile);
        return new Result(true,StatusCode.OK,"发送短信成功");
    }

    //用户注册
    @PostMapping("/resgister/{code}")
    public Result register(@PathVariable String code,@RequestBody User user){
        try {
            //用户注册
            userService.register(code,user);
            return new Result(true,StatusCode.OK,"用户注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"用户注册失败");
        }
    }

    //用户登录
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        //调用service层的方法判断用户是否登录
        User loginuser = userService.login(user);
        //调用jwt工具类生成token
        String token = jwtUtil.CreatJwt(user.getId(), user.getNickname(), "user");
        //定义一个集合用于向前端传输数据
        Map map = new HashMap();
        //向集合中添加数据
        map.put("token",token);
        map.put("name",user.getNickname());
        //判断返回结果
        if (loginuser != null){
            return new Result(true,StatusCode.OK,"用户登录成功",map);
        }else {
            return new Result(false,StatusCode.ERROR,"用户登录失败");
        }
    }

    //根据id删除用户
    @DeleteMapping("{userid}")
    public Result delete(@PathVariable String userid, HttpServletRequest request){
        //从请求作用域中获取roles对应的值
        Claims claims = (Claims) request.getAttribute("claims");
        //判断admin对应的值时候为空，是的话就执行删除的操作
        if (claims != null && claims.get("roles").equals("admin")){   //只有管理员权限才能删除
         userService.delete(userid); //删除用户
         return  new Result(true,StatusCode.OK,"删除用户成功");
        }
        return new Result(false,StatusCode.ERROR,"无权限删除用户");
    }
}
