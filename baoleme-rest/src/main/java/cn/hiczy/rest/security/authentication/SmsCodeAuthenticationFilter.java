package cn.hiczy.rest.security.authentication;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 短信验证码认证过滤器
 * @author saco
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String FZ_MOBILE_KEY = "mobile";
    private String mobileParameter = FZ_MOBILE_KEY;
    private boolean postOnly = true;

    public SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            //获取手机号
            String phoneNumber = this.obtainUsername(request);
            if (phoneNumber == null) {
                phoneNumber = "";
            }

            phoneNumber = phoneNumber.trim();
            SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(phoneNumber);
            this.setDetails(request, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }


    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(this.mobileParameter);
    }

    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setmobileParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.mobileParameter = usernameParameter;
    }


    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getMobileParameter() {
        return this.mobileParameter;
    }


}
