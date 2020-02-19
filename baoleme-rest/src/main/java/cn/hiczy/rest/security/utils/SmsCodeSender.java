package cn.hiczy.rest.security.utils;

import cn.hiczy.rest.exception.ValidateCodeException;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Component;

/**
 * @author saco
 */
@Component
public class SmsCodeSender implements  CodeSender {
    @Override
    public void send(String phoneNumber, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAILmi6xZqYxumY", "wU7N9VaST1sKw4T03Ezz7aWnOQNhgm");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("SignName", "fz外卖网");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("TemplateCode", "SMS_167530270");
        request.putQueryParameter("TemplateParam", "{\"code\": " + code + "}");

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            throw new ValidateCodeException("服务器异常，发送消息失败！");
        } catch (ClientException e) {
            throw new ValidateCodeException("服务器异常，发送消息失败！");
        }
    }
}
