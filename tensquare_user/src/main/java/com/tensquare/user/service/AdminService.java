package com.tensquare.user.service;

import com.tensquare.user.dao.AdminDao;
import com.tensquare.user.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utils.IdWorker;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;  //密码加密
    @Autowired
    private IdWorker idWorker;

    //添加管理员
    public void addAdmin(Admin admin) {
        //设置管理员id
        admin.setId(idWorker.nextId()+"");
        //给管理员密码进行加密
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        //保存用户信息到数据库
        adminDao.save(admin);
    }

    //管理员登录
    public Admin login(Admin admin) {
        //根据管理员登录名获取当前的管理员
        Admin ad = adminDao.findByLoginname(admin.getLoginname());
        //判断从数据库查询管理员的密码和用户输入的管理员密码是否一致
        if (ad != null &&  passwordEncoder.matches(admin.getPassword(),ad.getPassword())){
           //返回当前的用户
           return ad;
        }
       //返回空值
        return null;
    }

    //根据id删除管理员
    public void delete(String adminId) {
        adminDao.deleteById(adminId);
    }
}
