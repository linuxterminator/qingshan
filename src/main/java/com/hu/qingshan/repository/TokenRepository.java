package com.hu.qingshan.repository;

import com.hu.qingshan.model.DatabaseModel.RefreshToken;
import org.apache.ibatis.annotations.Param;

public interface TokenRepository {

    Integer insert(@Param("refreshToken") RefreshToken refreshToken);

}
