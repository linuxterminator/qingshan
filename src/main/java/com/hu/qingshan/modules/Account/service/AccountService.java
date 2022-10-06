package com.hu.qingshan.modules.Account.service;

import com.hu.qingshan.model.DTO.AccountDTO;
import com.hu.qingshan.model.RequestParam.Account.LoginParam;
import com.hu.qingshan.model.RequestParam.Account.SignupParam;

public interface AccountService {

    void wxLogin(String code);

    AccountDTO accountLogin(LoginParam loginParam);

    String accountSignup(SignupParam signupParam);

    String refreshAccessToken();

    void accoundLogout(String userId);

}
