package com.hu.qingshan.modules.token;

import com.hu.qingshan.model.DatabaseModel.RefreshToken;

public interface TokenService {

    Integer insert(RefreshToken refreshToken);

    Integer delete(String token);

    String isExistsOrExpire(String token);

    void isArreadyLoginOrExpire(String userId);

}
