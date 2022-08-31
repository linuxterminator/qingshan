package com.hu.qingshan.core.JwtUntil;

import com.hu.qingshan.model.DatabaseModel.RefreshToken;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Token {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static LocalDateTime expirDate;
    private static LocalDateTime createDate;

    public static <T> String GETAccessToken(T data){
        return Jwts
                .builder()
                .setClaims(Map.of("userClaims",data))
                .setIssuedAt(GetCreateDate())
                .setExpiration(GetExpireDate())
                .signWith(key)
                .compact();
    }

    public static RefreshToken GETRefreshToken(){
        return new RefreshToken();
    }

    public static void parseJWT(String jws){
        Jws<Claims> res = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws);
        System.out.println("过期时间"+res.getBody().getExpiration());
        System.out.println("发布时间"+res.getBody().getIssuedAt());
        System.out.println(res.getBody().get("userClaims"));
    }

    private static Date GetExpireDate(){
        expirDate = LocalDateTime.now().plusMinutes(5);
        return Date.from(expirDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    private static Date GetCreateDate(){
        createDate = LocalDateTime.now();
        return Date.from(createDate.atZone(ZoneId.systemDefault()).toInstant());
    }

}
