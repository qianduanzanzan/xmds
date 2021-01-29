package com.atxiaoming.utils;

import com.atxiaoming.entity.Customer;
import com.atxiaoming.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    private static final String TOKEN_SECRET = "privateKey";

    public String createToken(User user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");

            return JWT.create()
                    .withHeader(header)
                    .withClaim("userId", user.getId())
                    .withClaim("updateAt", user.getUpdateAt().toString())
                    .withClaim("a",new Date())
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String createCusToken(Customer customer) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");

            return JWT.create()
                    .withHeader(header)
                    .withClaim("phone", customer.getPhone())
                    .withClaim("a",new Date())
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getCusIdFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(token);
            System.out.println(jwt.getClaim("phone").asString());
            String phone = jwt.getClaim("phone").asString();
            return phone;
        } catch (Exception e){
            return "";
        }
    }

    public Integer getIdFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Integer userId = jwt.getClaim("userId").asInt();
            return userId;
        } catch (Exception e){
            return 0;
        }
    }

    public String getUpdateAtFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String updateAt_str = jwt.getClaim("updateAt").asString();
//            System.out.println(updateAt_str);
//            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            LocalDateTime updateAt = LocalDateTime.parse(updateAt_str,df);
            return updateAt_str;
        } catch (Exception e){
            return null;
        }
    }
}
