package com.hu.qingshan.mapper;

import com.hu.qingshan.model.DatabaseModel.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    Integer insert(@Param("user") User user);

    User selectByName(@Param("username") String username);

    Integer isUserExists(@Param("username") String username);

    User selectById(@Param("userId") String userId);

}
