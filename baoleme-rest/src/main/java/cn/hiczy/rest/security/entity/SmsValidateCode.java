package cn.hiczy.rest.security.entity;

import java.time.LocalDateTime;

/**
 * 短信验证码
 * @author saco
 */
public class SmsValidateCode extends ValidateCode {

    private String phoneNumber;


    public SmsValidateCode(String code, LocalDateTime expireTime, String phoneNumber) {
        super(code, expireTime);
        this.phoneNumber = phoneNumber;
    }

    public SmsValidateCode(String code, int seconds) {
        super(code, seconds);
        this.phoneNumber = phoneNumber;
    }
}
