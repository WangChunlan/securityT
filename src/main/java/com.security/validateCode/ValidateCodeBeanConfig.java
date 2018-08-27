package com.security.validateCode;

import com.security.newcode.ValidateCodeVerify;
import com.security.newcode.ValidateCodeVerifyImpl;
import com.security.properties.MySecurityProperties;
import com.security.validateCode.image.ImageCodeGenerator;
import com.security.validateCode.sms.DefaultSmsCodeSender;
import com.security.validateCode.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ValidateCodeBeanConfig
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/21 9:32
 * @Version 1.0
 **/
@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private MySecurityProperties mySecurityProperties;
    @Bean
    @ConditionalOnMissingBean(name="imageCodeGenerator")
//    @ConditionalOnMissingBean(ImageCodeGenerator.class)
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator codeGenerator=new ImageCodeGenerator();
        codeGenerator.setMySecurityProperties(mySecurityProperties);
        return codeGenerator;
    }

    @Bean
//    @ConditionalOnMissingBean(name="smsCodeSender")
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSender();
    }

    @Bean
//    @ConditionalOnMissingBean(name="smsCodeSender")
    @ConditionalOnMissingBean(ValidateCodeVerify.class)
    public ValidateCodeVerify validateCodeVerify(){
        return new ValidateCodeVerifyImpl();
    }


}
