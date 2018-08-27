package com.security.newcode;

import com.security.validateCode.ValidateCode;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;


@Component
public class SessionValidateCodeKeeperImpl implements ValidateCodeKeeper {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Override
    public void saveValidateCode(ServletWebRequest request, String SESSION_KEY_PREFIX, ValidateCode validateCode) {
        System.out.println(SESSION_KEY_PREFIX);
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX, validateCode);
    }

    @Override
    public ValidateCode getValidateCode(ServletWebRequest request, String SESSION_KEY_PREFIX) {
        System.out.println(SESSION_KEY_PREFIX);
        Object obj = sessionStrategy.getAttribute(request, SESSION_KEY_PREFIX);
        if (!(obj instanceof ValidateCode)) throw new RuntimeException("获取验证码类型异常");
        return (ValidateCode) obj;
    }

    @Override
    public void removeValidateCode(ServletWebRequest request, String SESSION_KEY_PREFIX) {
        sessionStrategy.removeAttribute(request, SESSION_KEY_PREFIX);
    }
}
