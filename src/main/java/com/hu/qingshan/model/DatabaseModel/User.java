package com.hu.qingshan.model.DatabaseModel;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.Data;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 1.用户id
 * 2.用户名
 * 3.用户密码
 * 4.用户头像
 * 5.用户状态
 * 6.用户创建时间
 */
@Data
public class User extends BaseModel{

    private String userId;
    private String username;
    private String password;
    private String icon;
    private String nickname;
    private String status;

    public User initAttribute(){
        this.userId = GetNanoid();
        this.status = "normal";
        this.icon = "https://qingshanblog.oss-cn-hangzhou.aliyuncs.com/pic_quark_1585238620674.jpg";
        return this;
    }

    public User EncryptPassword(){
        this.password = DigestUtils.md5DigestAsHex(this.password.getBytes(StandardCharsets.UTF_8));
        return this;
    }

    public Boolean PasswordMatch(String password){
        String encrypted = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        return this.password.equals(encrypted);
    }

}
