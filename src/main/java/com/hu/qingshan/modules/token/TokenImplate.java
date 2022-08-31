package com.hu.qingshan.modules.token;

import com.hu.qingshan.model.DatabaseModel.RefreshToken;
import com.hu.qingshan.repository.TokenRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenImplate implements TokenService{

    private final TokenRepository tokenRepository;

    public TokenImplate(TokenRepository tokenRepository){
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Integer insert(RefreshToken refreshToken) {
        return tokenRepository.insert(refreshToken);
    }

    @Override
    public Integer delete(String token) {
        return null;
    }

    @Override
    public RefreshToken isExistsOrExpire(String token) {
        return null;
    }

}
