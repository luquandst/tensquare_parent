package com.tensquare.user.dao;

import com.tensquare.user.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin,String> {
    //根据管理员名称查询当前管理员
    Admin findByLoginname(String loginname);
}
