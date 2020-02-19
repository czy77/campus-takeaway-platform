package cn.hiczy.rest.controller.user;

import cn.hiczy.rest.security.entity.SmsValidateCode;
import cn.hiczy.rest.security.utils.SmsCodeGenerator;
import cn.hiczy.rest.security.utils.SmsCodeSender;
import cn.hiczy.vo.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class SmsCodeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private SmsCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    private ObjectMapper objectMapper = new ObjectMapper();


    public final static String SESSION_KEY_SMS_CODE = "SESSION_KEY_SMS_CODE";


    @GetMapping("/smsCode")
    public void sendCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8;");
        //获取用户的手机号
        String phoneNumber = request.getParameter("mobile");
        if ("".equals(phoneNumber)  || phoneNumber == null) {
            response.getWriter().write(objectMapper.writeValueAsString(CommonResult.failure("手机号码不能为空")));
        }
        logger.info("手机号码是:" + phoneNumber);

        //生成验证码
        SmsValidateCode smsCode = smsCodeGenerator.generate();
        logger.info("验证码是:  " + smsCode.getCode());

        //不放入session中，而是放入redis中
        redisTemplate.opsForValue().set(phoneNumber, smsCode);


        //将短信验证码下发给用户
        smsCodeSender.send(phoneNumber, smsCode.getCode());
        response.getWriter().write(objectMapper.writeValueAsString(CommonResult.success("验证码已发送")));
    }

}
