package com.security.validateCode.sms;

public interface SmsCodeSender {

    void send (String mobile,String code);
}
