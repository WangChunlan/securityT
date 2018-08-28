package com.security.newcode;

import com.security.exception.ValidateCodeException;
import com.security.validateCode.ValidateCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;


public class ValidateCodeVerifyImpl implements ValidateCodeVerify {
    @Autowired
    private SessionValidateCodeKeeperImpl sessionValidateCodeKeeper;

    @Override
    public void verify(ServletWebRequest request, String codeInRequest, ValidateCodeType type) throws ValidateCodeException {
        String SESSION_KEY_PREFIX = type.getSessionKey();
        ValidateCode validateCode = sessionValidateCodeKeeper.getValidateCode(request, SESSION_KEY_PREFIX);
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (validateCode == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (validateCode.isExpried()) {
            sessionValidateCodeKeeper.removeValidateCode(request, SESSION_KEY_PREFIX);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(validateCode.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionValidateCodeKeeper.removeValidateCode(request, SESSION_KEY_PREFIX);
    }
}
