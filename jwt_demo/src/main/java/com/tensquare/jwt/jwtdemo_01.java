package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class jwtdemo_01 {

    @Test  //生成token
    public void test01(){
        //1.生成jwtbuilder对象
        JwtBuilder jwtBuilder = Jwts.builder().setId("8888")
                .setIssuedAt(new Date())
                .setSubject("admin")
                .signWith(SignatureAlgorithm.HS256, "zelin");
        //2.生成token
        String token = jwtBuilder.compact();
        //3.打印出token的值
        System.out.println("token = " + token);
    }

    @Test //解析token
    public void test02(){
        //1.定义当前的token
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODg4IiwiaWF0IjoxNTY5MjE5NTEzLCJzdWIiOiJhZG1pbiJ9.fgPWUH6Vqj-GXiVx6v0_hxurLDNIUNB1nYdWijcohSg";
        //2.解析当前的token
        Claims zelin = Jwts.parser().setSigningKey("zelin").parseClaimsJws(token).getBody(); //获取claim对象
        String id = zelin.getId(); //获取id
        System.out.println("id = " + id);
        Date date = zelin.getIssuedAt(); //获取日期
        System.out.println("date = " + date);
        String subject = zelin.getSubject(); //获取主体
        System.out.println("subject = " + subject);
    }
}
