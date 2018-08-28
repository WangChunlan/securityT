package com.security.validateCode;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName ValidateCodeGenerator
 * @Description 校验码生成器
 * @Author wangchunlan
 * @Date 2018/8/20 16:42
 * @Version 1.0
 **/
public interface ValidateCodeGenerator {

    /**
     * @Author wangchunlan
     * @Description 生成校验码
     * @Date 16:46 2018/8/20
     * @Param [request]
     * @return com.security.validateCode.ValidateCode
     **/
    ValidateCode generate(ServletWebRequest request);
}
