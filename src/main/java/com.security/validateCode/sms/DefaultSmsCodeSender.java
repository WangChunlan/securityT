package com.security.validateCode.sms;

/**
 * @ClassName DefaultSmsCodeSender
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/22 9:46
 * @Version 1.0
 **/
public class DefaultSmsCodeSender implements  SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.print("向手机"+mobile+"发生短信验证码:"+code);
    }
}
