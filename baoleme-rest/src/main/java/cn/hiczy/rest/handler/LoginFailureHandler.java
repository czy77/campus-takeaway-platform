package cn.hiczy.rest.handler;


import cn.hiczy.vo.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败时的处理逻辑
 * @author saco
 */
@Component("failureHandler")
public class LoginFailureHandler implements AuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper mapper;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        CommonResult failure = CommonResult.failure(e.getMessage());
        String json = mapper.writeValueAsString(failure);
        logger.info("登录失败>>>>>>>" + json);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
