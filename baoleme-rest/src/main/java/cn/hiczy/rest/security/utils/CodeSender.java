package cn.hiczy.rest.security.utils;

/**
 * @author saco
 */
public interface CodeSender {
    /**
     * 发送验证码
     * @param phoneNumber 手机号码
     * @param code  验证码
     */
    void send(String phoneNumber, String code);
}
