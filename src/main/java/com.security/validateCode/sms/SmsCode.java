package com.security.validateCode.sms;

import com.security.validateCode.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @ClassName ImageCode
 * @Description TODO    短信验证码
 * @Author wangchunlan
 * @Date 2018/8/20 9:40
 * @Version 1.0
 **/
public class SmsCode extends ValidateCode {

    public SmsCode(String code, int expireIn) {
        super(code, expireIn);
    }

    public SmsCode(String code, LocalDateTime expireTime) {
        super(code, expireTime);
    }
}
