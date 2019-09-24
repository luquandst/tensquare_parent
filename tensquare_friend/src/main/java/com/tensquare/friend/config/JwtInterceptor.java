package com.tensquare.friend.config;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头
        String header = request.getHeader("Authorization");
        //判断header是否已Bearer开头
        if (StringUtils.isNotEmpty(header)&& header.startsWith("Bearer ")){
            //获取token
            String token = header.substring(7);
            //解析tokend
            Claims claims = jwtUtil.parseJwt(token);
            //将claims放到请求作用域中
            request.setAttribute("claims",claims);
        }
        return true;
    }
}
