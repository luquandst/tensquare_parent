package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class jwtdemo_03 {

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
                .claim("roles", "admin")
                .signWith(SignatureAlgorithm.HS256, "zelin");
        //生成token
        String token = jwtBuilder.compact();
        //打印出token
        System.out.println("token = " + token);
    }

    @Test //解析token
    public void test02(){
        //定义token
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODg4IiwiaWF0IjoxNTY5MjIxODQxLCJzdWIiOiJhZG1pbiIsImV4cCI6MTU2OTIyMjE0MSwicm9sZXMiOiJhZG1pbiJ9.zrsjsIVR556cbRXofxComD94e6ixSmYf7ieDd7Xb9eo";
        //解析token,获取claim对象
        Claims zelin = Jwts.parser().setSigningKey("zelin").parseClaimsJws(token).getBody();
        String id = zelin.getId(); //获取id
        System.out.println("id = " + id);
        Date issuedAt = zelin.getIssuedAt(); //获取生成token的时间
        System.out.println("issuedAt = " + issuedAt);
        Date expiration = zelin.getExpiration(); //获取的token失效的时间
        System.out.println("expiration = " + expiration);
        String subject = zelin.getSubject(); //获取主体
        System.out.println("subject = " + subject);
        String  roles = (String) zelin.get("roles");
        System.out.println("roles = " + roles);

    }
}
