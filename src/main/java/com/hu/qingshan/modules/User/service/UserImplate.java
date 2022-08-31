package com.hu.qingshan.modules.User.service;

import com.hu.qingshan.core.convert.ModelConvert;
import com.hu.qingshan.model.DatabaseModel.User;
import com.hu.qingshan.model.RequestParam.SignupParam;
import com.hu.qingshan.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserImplate extends ModelConvert implements UserService{

    private final UserRepository userRepository;

    public UserImplate(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Boolean isUserExists(String username){
        return userRepository.isUserExists(username) == 1;
    }

    @Override
    public User selectByName(String username) {
        return userRepository.selectByName(username);
    }

    @Override
    public String insert(SignupParam signupParam) {
        User user = ConvertToTarget(signupParam,User.class).initAttribute().EncryptPassword();
        userRepository.insert(user);
        return user.getUserId();
    }

}
