package com.hu.qingshan.repository;

import com.hu.qingshan.model.DatabaseModel.User;
import org.apache.ibatis.annotations.Param;

public interface UserRepository {

    Integer insert(@Param("user") User user);

    User selectByName(@Param("username") String username);

    Integer isUserExists(@Param("username") String username);

}
