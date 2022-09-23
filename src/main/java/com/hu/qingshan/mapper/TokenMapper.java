package com.hu.qingshan.mapper;

import com.hu.qingshan.model.DatabaseModel.RefreshToken;
import org.apache.ibatis.annotations.Param;

public interface TokenMapper {

    Integer insert(@Param("refreshToken") RefreshToken refreshToken);

    RefreshToken selectByUserId(@Param("userId") String userId);

    Integer deleteByUserId(@Param("userId") String userId);

    RefreshToken selectByToken(@Param("refreshtoken") String refreshToken);

}
