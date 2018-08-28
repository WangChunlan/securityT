package com.security.controller;

import com.security.utils.SecurityConstants;
import com.security.validateCode.ValidateCodeGenerator;
import com.security.validateCode.image.ImageCode;
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

    @GetMapping(SecurityConstants.DEFAULT_IMAGECODE_PROCESSING_URL)
    public void createCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("image/jpg");
        ImageCode imageCode=(ImageCode)imageCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SecurityConstants.DEFAULT_SESSION_KEY_FOR_CODE_IMAGE,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }

    @GetMapping(SecurityConstants.DEFAULT_SMSCODE_PROCESSING_URL)
    public void createSmsCode(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletRequestBindingException {
        SmsCode smsCode=(SmsCode)smsCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SecurityConstants.DEFAULT_SESSION_KEY_FOR_CODE_SMS,smsCode);
        // 短信运行商
        String mobile= ServletRequestUtils.getRequiredStringParameter(request,SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);
        smsCodeSender.send(mobile,smsCode.getCode());

    }

}
