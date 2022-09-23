package com.hu.qingshan.modules.token;

import com.hu.qingshan.core.ExceptionAndEnums.AccountExceptionEnums;
import com.hu.qingshan.model.DatabaseModel.RefreshToken;
import com.hu.qingshan.mapper.TokenMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenImplate implements TokenService{

    private final TokenMapper tokenRepository;

    public TokenImplate(TokenMapper tokenRepository){
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Integer insert(RefreshToken refreshToken) {
        return tokenRepository.insert(refreshToken);
    }

    @Override
    public Integer delete(String userId) {
        return tokenRepository.deleteByUserId(userId);
    }

    @Override
    public String isExistsOrExpire(String token) {
        RefreshToken refreshToken = tokenRepository.selectByToken(token);

        LocalDateTime expire = Optional
                .ofNullable(refreshToken)
                .orElseThrow(()->new RuntimeException("refreshToken不存在"))
                .getExpireDate();

        if(!isExpire(expire)){
            tokenRepository.deleteByUserId(refreshToken.getUserId());
            throw AccountExceptionEnums.REFRESHTOKEN_EXPIRE.getException();
        }

        return refreshToken.getUserId();
    }

    @Override
    public void isArreadyLoginOrExpire(String userId) {

        RefreshToken refreshToken = tokenRepository.selectByUserId(userId);

        if(refreshToken != null && isExpire(refreshToken.getExpireDate())){
            throw AccountExceptionEnums.ALREADY_LOGIN.getException();
        }

        // 如果已经过期则删除已经过期的
        if(refreshToken != null && !isExpire(refreshToken.getExpireDate())){
            tokenRepository.deleteByUserId(userId);
        }

    }

    private Boolean isExpire(LocalDateTime createTime){
        return createTime.isAfter(LocalDateTime.now());
    }

}
