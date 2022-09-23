package com.hu.qingshan.modules.User.service;

import com.hu.qingshan.core.convert.ModelConvert;
import com.hu.qingshan.model.DatabaseModel.User;
import com.hu.qingshan.model.RequestParam.Account.SignupParam;
import com.hu.qingshan.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserImplate extends ModelConvert implements UserService{

    private final UserMapper userMapper;

    public UserImplate(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Boolean isUserExists(String username){
        return userMapper.isUserExists(username) == 1;
    }

    @Override
    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }

    @Override
    public String insert(SignupParam signupParam) {
        User user = ConvertToTarget(signupParam,User.class).initAttribute().EncryptPassword();
        userMapper.insert(user);
        return user.getUserId();
    }

}
