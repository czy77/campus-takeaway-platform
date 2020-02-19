package cn.hiczy.rest.security.utils;

import cn.hiczy.rest.security.entity.SmsValidateCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;


/**
 * @author saco
 */
@Component
public class SmsCodeGenerator {

    /**
     * 生成随机的6位验证码,5分钟失效
     *
     * @return
     */
    public SmsValidateCode generate() {
        String code = RandomStringUtils.randomNumeric(6);

        return new SmsValidateCode(code, 5 * 60);
    }

}
