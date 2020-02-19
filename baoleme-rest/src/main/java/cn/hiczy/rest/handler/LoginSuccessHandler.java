package cn.hiczy.rest.handler;

import cn.hiczy.rest.security.entity.User;
import cn.hiczy.rest.security.service.MyUserService;
import cn.hiczy.rest.vo.UserInfoVo;
import cn.hiczy.vo.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登录成功时执行
 * @author saco
 */
@Component("successHandler")
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MyUserService myUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserInfoVo userInfoVo = new UserInfoVo();

        User user = (User) authentication.getPrincipal();
        BeanUtils.copyProperties(user, userInfoVo);

        CommonResult<UserInfoVo> success = CommonResult.success(userInfoVo);
        String json = mapper.writeValueAsString(success);
        logger.info("登录成功:" + json);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

}
