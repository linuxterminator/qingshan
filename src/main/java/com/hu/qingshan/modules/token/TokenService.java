package com.hu.qingshan.modules.token;

import com.hu.qingshan.model.DatabaseModel.RefreshToken;

public interface TokenService {

    Integer insert(RefreshToken refreshToken);

    Integer delete(String token);

    RefreshToken isExistsOrExpire(String token);

}
