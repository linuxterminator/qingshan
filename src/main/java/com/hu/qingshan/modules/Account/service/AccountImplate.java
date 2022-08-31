package com.hu.qingshan.modules.Account.service;

import com.hu.qingshan.core.JwtUntil.Token;
import com.hu.qingshan.core.convert.ModelConvert;
import com.hu.qingshan.model.DatabaseModel.RefreshToken;
import com.hu.qingshan.model.DatabaseModel.User;
import com.hu.qingshan.model.OutsideConfig.WXinfo;
import com.hu.qingshan.model.ReponseModel.AccountResponse;
import com.hu.qingshan.model.ReponseModel.WXResponse;
import com.hu.qingshan.model.RequestParam.LoginParam;
import com.hu.qingshan.model.RequestParam.SignupParam;
import com.hu.qingshan.modules.User.service.UserService;
import com.hu.qingshan.modules.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class AccountImplate extends ModelConvert implements AccountService {

    private final WXinfo wXinfo;
    private final RestTemplate restTemplate;

    private final UserService userService;
    private final TokenService tokenService;

    public AccountImplate(WXinfo wXinfo, RestTemplate restTemplate,UserService userService,TokenService tokenService){
        this.wXinfo = wXinfo;
        this.restTemplate = restTemplate;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    // 微信账户登陆
    @Override
    public void wxLogin(String code) {

        String convertedURL = wXinfo
                .getUrl()
                .replace("APPID", wXinfo.getAppid())
                .replace("SECRET", wXinfo.getSecret())
                .replace("JSCODE",code);

        WXResponse wxResponse = restTemplate.getForObject(convertedURL,WXResponse.class);

        if(wxResponse.getErrcode()!=null){
            // todo 抛出请求异常
        }

        // 生成jws
        String jwt = Token.GETAccessToken(Arrays.asList(wxResponse.getOpenid(),wxResponse.getSession_key()));

        // 解析jws
        Token.parseJWT(jwt);

    }

    // 账户登陆
    @Transactional
    @Override
    public AccountResponse accountLogin(LoginParam loginParam) {

        if(!userService.isUserExists(loginParam.getUsername())){
            throw new RuntimeException("用户不存在!");
        }

        User user = userService.selectByName(loginParam.getUsername());

        if(!user.PasswordMatch(loginParam.getPassword())){
            throw new RuntimeException("用户密码错误!");
        }

        return TokenGenerator(user);

    }

    // 存储refreshToken
    private void storageRefreshToken(RefreshToken refreshToken){
        tokenService.insert(refreshToken);
    }

    // 生成token
    private AccountResponse TokenGenerator(User user){

        String accessToken = Token.GETAccessToken(user.getUserId());
        RefreshToken refreshToken = Token.GETRefreshToken();

        AccountResponse accountResponse = ConvertToTarget(user, AccountResponse.class);
        accountResponse.setToken(new AccountResponse.Token(accessToken,refreshToken.getRefreshToken()));

        storageRefreshToken(refreshToken);

        return accountResponse;
    }

    // 账户注册
    @Transactional
    @Override
    public String accountSignup(SignupParam signupParam) {

        if(userService.isUserExists(signupParam.getUsername())){
            throw new RuntimeException("用户名已存在!");
        }

        userService.insert(signupParam);

        return "注册成功";

    }

    // 刷新accessToken
    @Override
    public String refreshAccessToken() {

        if(tokenService.isExistsOrExpire(null)==null){
            throw new RuntimeException("登陆状态已过期!");
        }

        String newAccessToken = Token.GETAccessToken(null);

        return null;
    }

}
