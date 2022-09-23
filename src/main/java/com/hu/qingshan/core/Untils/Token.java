package com.hu.qingshan.core.Untils;

import com.hu.qingshan.model.DatabaseModel.RefreshToken;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

public class Token {
    private static final String secret = "XdPDCQ2m2iRZ29dFSHyU83xOIZ7w31U8CsJZkmqQN9v6KaXXZt";

    private static Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    private static LocalDateTime expirDate;
    private static LocalDateTime createDate;
    public static <T> String GETAccessToken(T data){
        return Jwts
                .builder()
                .setClaims(Map.of("userClaims",data))
                .setIssuedAt(GetCreateDate())
                .setExpiration(GetExpireDate())
//                默认key的加密是什么
                .signWith(key)
                .compact();
    }

    public static RefreshToken refreshToken(){
        return new RefreshToken();
    }

    public static void parseJWT(String jws){
        Jws<Claims> res = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws);
    }

    private static Date GetExpireDate(){
        // 后端测试使用
        expirDate = LocalDateTime.now().plusHours(10);
        return Date.from(expirDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    private static Date GetCreateDate(){
        createDate = LocalDateTime.now();
        return Date.from(createDate.atZone(ZoneId.systemDefault()).toInstant());
    }

}
