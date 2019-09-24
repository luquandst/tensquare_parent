package utils;

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtUtil {
    private String key ;
    private long ttl;  //过期时间

    //创建jwt
    public String CreatJwt(String id,String subject,String roles){
        //设置当前的时间
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        //创建jwtbuilder对象
        JwtBuilder jwtBuilder = Jwts.builder().setId(id)
                .setIssuedAt(date)
                .setSubject(subject)
                .setExpiration(new Date(time + ttl))
                .claim("roles", roles)
                .signWith(SignatureAlgorithm.HS256, key);
        //生成并返回token
        //判断是否过期
        if (ttl > 0){
            String token = jwtBuilder.compact();
            return token;
        }
        return null ;
    }

    //解析jwt
    public Claims parseJwt(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
