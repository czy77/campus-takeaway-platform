package cn.hiczy.rest.security.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author saco
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    Logger logger = LoggerFactory.getLogger(getClass());


    private UserDetailsService userService;


    public void setUserService(UserDetailsService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //接收从SmsCodeAuthenticationFilter 传来的 token
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

        logger.info("获取的用户名为:" + (String) authenticationToken.getPrincipal());

        //读取用户信息
        UserDetails user = userService.loadUserByUsername((String) authenticationToken.getPrincipal());

        if (user == null) {
            throw new InternalAuthenticationServiceException("无法读取用户信息");
        }

        //如果有这个用户，更新它的认证状态设为，表示认证通过
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
