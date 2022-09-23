package com.hu.qingshan.modules.Account.service;

import com.hu.qingshan.core.ExceptionAndEnums.AccountExceptionEnums;
import com.hu.qingshan.core.Untils.Token;
import com.hu.qingshan.core.convert.ModelConvert;
import com.hu.qingshan.model.DatabaseModel.RefreshToken;
import com.hu.qingshan.model.DatabaseModel.User;
import com.hu.qingshan.core.ConfigProperties.WXProperties;
import com.hu.qingshan.model.ReponseViewModel.AccountResponse;
import com.hu.qingshan.model.ReponseViewModel.WXResponse;
import com.hu.qingshan.model.RequestParam.Account.LoginParam;
import com.hu.qingshan.model.RequestParam.Account.SignupParam;
import com.hu.qingshan.modules.User.service.UserService;
import com.hu.qingshan.modules.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
public class AccountImplate extends ModelConvert implements AccountService {

    private final WXProperties wXinfo;
    private final RestTemplate restTemplate;

    private final UserService userService;
    private final TokenService tokenService;

    public AccountImplate(WXProperties wXinfo, RestTemplate restTemplate, UserService userService, TokenService tokenService){
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
            throw AccountExceptionEnums.USERNAME_OR_PASSWORD_VERIFY.getException();
        }

        User user = userService.selectByName(loginParam.getUsername());

        if(!user.PasswordMatch(loginParam.getPassword())){
            throw AccountExceptionEnums.USERNAME_OR_PASSWORD_VERIFY.getException();
        }

        isAlreadyLoginCheck(user.getUserId());

        return AccountResponseGenerator(user);

    }

    // 存储refreshToken
    private void storageRefreshToken(RefreshToken refreshToken){
        tokenService.insert(refreshToken);
    }

    // 是否已经登陆
    private void isAlreadyLoginCheck(String userId){
        tokenService.isArreadyLoginOrExpire(userId);
    }

    // 退出登陆，如果accesstoken没有过期，则把accesstoken加入黑名单
    @Override
    public void accoundLogout(String userId) {

        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String accesstoken = httpServletRequest.getHeader("token");
//        tokenService.delete();
    }

    // 生成token
    private AccountResponse AccountResponseGenerator(User user){

        String accessToken = Token.GETAccessToken(user.getUserId());
        RefreshToken refreshToken = Token.refreshToken().initExpire();
        refreshToken.setUserId(user.getUserId());

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
            throw AccountExceptionEnums.USERNAME_ALREADY_EXISTS.getException();
        }

        userService.insert(signupParam);

        return "注册成功";

    }

    // 刷新accessToken
    @Override
    public String refreshAccessToken() {

        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String refreshtoken = httpServletRequest.getHeader("refreshtoken");

        String userId = tokenService.isExistsOrExpire(refreshtoken);

        return Token.GETAccessToken(userId);
    }

}
