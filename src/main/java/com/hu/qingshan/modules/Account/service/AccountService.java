package com.hu.qingshan.modules.Account.service;

import com.hu.qingshan.model.ReponseModel.AccountResponse;
import com.hu.qingshan.model.RequestParam.LoginParam;
import com.hu.qingshan.model.RequestParam.SignupParam;

public interface AccountService {

    void wxLogin(String code);

    AccountResponse accountLogin(LoginParam loginParam);

    String accountSignup(SignupParam signupParam);

    String refreshAccessToken();

}
