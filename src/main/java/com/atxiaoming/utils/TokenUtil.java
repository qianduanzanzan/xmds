package com.atxiaoming.utils;

import com.atxiaoming.entity.TbUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtil {

    public String createToken(TbUser user) {
        String token="";
        Date date = new Date();
        token= JWT.create().withAudience(user.getId().toString() + date)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
