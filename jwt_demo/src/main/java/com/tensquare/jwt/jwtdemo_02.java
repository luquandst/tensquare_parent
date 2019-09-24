package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class jwtdemo_02 {

    @Test //生成token
    public void test01(){
        //获取时间戳
        long currentTimeMillis = System.currentTimeMillis();
        //定义一个日期对象
        Date date = new Date(currentTimeMillis + 300 * 1000);
        //创建一个jwtbuilder对象
        JwtBuilder jwtBuilder = Jwts.builder().setId("8888")
                .setIssuedAt(new Date())
                .setSubject("admin")
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256, "zelin");
        //生成token对象
        String token = jwtBuilder.compact();
        //打印出token
        System.out.println("token = " + token);
    }

    @Test //解析token
    public void test02(){
        //定义token对象
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODg4IiwiaWF0IjoxNTY5MjIwNjI2LCJzdWIiOiJhZG1pbiIsImV4cCI6MTU2OTIyMDkyNn0.rCPem9NMZg333apw053hcRUkZW6pXQl92qGAmDqycO4";
        //创建claim对象
        Claims zelin = Jwts.parser().setSigningKey("zelin").parseClaimsJws(token).getBody();
        //解析token中的参数
        String id = zelin.getId();   //获取id
        System.out.println("id = " + id);
        Date date = zelin.getIssuedAt(); //获取开始时间
        System.out.println("date = " + date);
        String subject = zelin.getSubject(); //获取主体
        System.out.println("subject = " + subject);
        Date expiration = zelin.getExpiration(); //获取token失效时间
        System.out.println("expiration = " + expiration);
    }
}
