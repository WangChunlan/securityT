package com.security.validateCode.sms;

import com.security.properties.MySecurityProperties;
import com.security.validateCode.ValidateCode;
import com.security.validateCode.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName ImageCodeGenerator
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/21 13:05
 * @Version 1.0
 **/
@Component("smsCodeGenerator") // TODO 自己添加的
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private MySecurityProperties mySecurityProperties;

    /**
     * 生成随机数字
     * @param request
     * @return
     */
    @Override
    public ValidateCode generate(ServletWebRequest request) {
        int expireIn=ServletRequestUtils.getIntParameter(request.getRequest(),"expireIn",mySecurityProperties.getCode().getSms().getExpireIn());


        String code=RandomStringUtils.randomNumeric(mySecurityProperties.getCode().getSms().getLength());

        return new SmsCode(code,expireIn);
    }

    public MySecurityProperties getMySecurityProperties() {
        return mySecurityProperties;
    }

    public void setMySecurityProperties(MySecurityProperties mySecurityProperties) {
        this.mySecurityProperties = mySecurityProperties;
    }
}
