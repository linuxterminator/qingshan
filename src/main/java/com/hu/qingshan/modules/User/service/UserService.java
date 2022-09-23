package com.hu.qingshan.modules.User.service;

import com.hu.qingshan.model.DatabaseModel.User;
import com.hu.qingshan.model.RequestParam.Account.SignupParam;

public interface UserService {

    Boolean isUserExists(String username);

    User selectByName(String username);

    String insert(SignupParam signupParam);

}
