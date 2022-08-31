package com.hu.qingshan.modules.Account.controller;

import com.hu.qingshan.model.ReponseModel.AccountResponse;
import com.hu.qingshan.model.RequestParam.LoginParam;
import com.hu.qingshan.model.RequestParam.SignupParam;
import com.hu.qingshan.modules.Account.service.AccountService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/wxlogin")
    public void wxLogin(@RequestParam("code") String jscode){
        accountService.wxLogin(jscode);
    }

    @PostMapping("/accountlogin")
    public AccountResponse accountlogin(@Valid @RequestBody LoginParam loginParam){
        return accountService.accountLogin(loginParam);
    }

    @PostMapping("/accountsignup")
    public String accountsignup(@Valid @RequestBody SignupParam signupParam){
        return accountService.accountSignup(signupParam);
    }

    @GetMapping("/refreshtoken")
    public String refreshToken(){
        return accountService.refreshAccessToken();
    }

    @GetMapping("/logout")
    public String logout(){
        return null;
    }

}
