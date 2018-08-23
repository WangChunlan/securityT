package com.security.controller;

import com.security.validateCode.ValidateCodeBeanConfig;
import com.security.validateCode.ValidateCodeGenerator;
import com.security.validateCode.image.ImageCode;
import com.security.validateCode.image.ImageCodeGenerator;
import com.security.validateCode.sms.SmsCode;
import com.security.validateCode.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ValidateCodeController
 * @Description 验证码生成
 * @Author wangchunlan
 * @Date 2018/8/20 9:45
 * @Version 1.0
 **/
@RestController
public class ValidateCodeController {
    public static final String SESSION_KEY="SESSION_KEY_IMAGE_CODE";
    public static final String SESSION_KEY_SMS="SESSION_KEY_SMS_CODE";

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();


    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;
    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    /**
     * @Author wangchunlan
     * @Description //TODO
     * 创建验证码的过程：
     * 生成--》存入session-->发送出去
     * @Date 10:15 2018/8/22
     * @Param [response, request]
     * @return void
     **/

    @GetMapping("/code/image")
    public void createCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("image/jpg");
        ImageCode imageCode=(ImageCode)imageCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }

    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletRequestBindingException {
        SmsCode smsCode=(SmsCode)smsCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY_SMS,smsCode);
        // 短信运行商
        String mobile= ServletRequestUtils.getRequiredStringParameter(request,"mobile");
        smsCodeSender.send(mobile,smsCode.getCode());



    }

}
