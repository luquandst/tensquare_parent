package com.tensquare.user.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 鉴权拦截器
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("正在调用拦截器");
        //获取请求头
        String header = request.getHeader("Authorization");
        //判断请求头是否为空以及是否已Bearer开心
        if (StringUtils.isNotEmpty(header) && header.startsWith("Bearer ")){
            //截取token
            String token = header.substring(7);
            //解析token
            Claims claims = jwtUtil.parseJwt(token);
            //如果token不为空的话，就将token设置到的请求作用域中
            System.out.println("调用拦截器的方法...");
            if (claims != null){
                request.setAttribute("claims",claims);
            }
        }
        return true;
    }
}
