package cn.hiczy.rest.security.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 抽象验证码
 * @author saco
 */
@Data
public class ValidateCode implements Serializable {
    /**验证码*/
    protected String code;

    /**过期时间*/
    protected LocalDateTime expireTime;

    /**
     * 生成一个验证码
     * @param code
     * @param expireTime
     */
    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    /**
     * 设置验证码 seconds秒后过期
     * @param code
     * @param seconds
     */
    public ValidateCode(String code, int seconds) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(seconds);
    }


    /**验证码是否过期*/
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expireTime);
    }



}
