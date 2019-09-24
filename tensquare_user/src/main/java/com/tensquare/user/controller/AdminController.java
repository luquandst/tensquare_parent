package com.tensquare.user.controller;

import com.tensquare.user.pojo.Admin;
import com.tensquare.user.service.AdminService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtUtil jwtUtil;

    //添加管理员
    @PostMapping()
    public Result addAdmin(@RequestBody Admin admin){
        System.out.println("添加管理员");
        //添加管理员
        adminService.addAdmin(admin);
        //返回页面结果
        return new Result(true,StatusCode.OK,"添加管理员成功");
    }
    //管理员登录
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        //根据当前登录的用户获取用户
        Admin ad = adminService.login(admin);
        //生成鉴权使用的token
        String token = jwtUtil.CreatJwt(admin.getId(), admin.getLoginname(), "admin");
        //定义一个map集合用于向前端传输数据
        Map<Object, Object> map = new HashMap<>();
        //往map中添加数据
        map.put("token",token);
        map.put("name",admin.getLoginname());
        //判断用户是否存在
        if (ad != null){
            return new Result(true,StatusCode.OK,"管理员登录成功",map);
        }else {
            return  new Result(false,StatusCode.ERROR,"管理员登录失败");
        }
    }

    //根据id删除管理员
    @DeleteMapping("{adminId}")
    public Result delete(@PathVariable String adminId, HttpServletRequest request){
        //从请求作用域中获取claims对应的值
        Claims claims = (Claims) request.getAttribute("claims");
        //判断admin是否为空，不为空的话就可以执行删除
        if (claims != null && claims.get("roles").equals("admin")){
            adminService.delete(adminId);  //删除
            return new Result(true,StatusCode.OK,"根据id删除成功");
        }
        return new Result(false,StatusCode.ERROR,"无权限删除");
    }
}
