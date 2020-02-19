package cn.hiczy.rest.security.filter;


import cn.hiczy.rest.controller.user.SmsCodeController;
import cn.hiczy.rest.exception.ValidateCodeException;
import cn.hiczy.rest.security.entity.SmsValidateCode;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * 登录或注册时启用
 * @author saco
 */
@Component("smsCodeCheckFilter")
public class SignUpSmsCodeCheckFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //如果是登录请求或注册请求且请求方法为POST才处理
        boolean isPhoneLogin = StringUtils.equals("/authentication/mobile", httpServletRequest.getRequestURI());
        boolean isSignupRequest = StringUtils.equals("signUp", httpServletRequest.getRequestURI());
        boolean isPost  = StringUtils.equalsIgnoreCase("POST", httpServletRequest.getMethod());
        boolean toProcess = isPost && (isPhoneLogin || isSignupRequest);
        if (toProcess) {
            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (ValidateCodeException e) {
                failureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validate(ServletWebRequest servletWebRequest) throws ValidateCodeException, ServletRequestBindingException {

        //从请求中获取手机号
        String phoneNumber = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "mobile");

        //根据手机号从redis中取出验证码
        SmsValidateCode codeInSession = (SmsValidateCode) redisTemplate.opsForValue().get(phoneNumber);

        //从请求中获取验证码
        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "pwdOrSmsCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpired()) {
            redisTemplate.delete(phoneNumber);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInRequest, codeInSession.getCode())) {
            throw new ValidateCodeException("验证码不匹配");
        }

        //到达这里说明验证成功，将验证码从redis中移除
        redisTemplate.delete(phoneNumber);
    }


}
