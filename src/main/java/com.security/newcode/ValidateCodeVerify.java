package com.security.newcode;

import com.security.exception.ValidateCodeException;
import org.springframework.web.context.request.ServletWebRequest;


public interface ValidateCodeVerify {
    /**
     * 验证码，校验器
     *
     * @param request
     * @param codeInRequest 前端传过来的验证码内容
     * @param type          校验器类型：SMS还是IMAGE
     * @throws ValidateCodeException
     */
    void verify(ServletWebRequest request, String codeInRequest, ValidateCodeType type) throws ValidateCodeException;
}
