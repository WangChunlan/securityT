package com.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName ValidateCodeException
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/20 10:01
 * @Version 1.0
 **/
public class ValidateCodeException extends AuthenticationException {


    private static final long serialVersionUID = -3781754157576703051L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
