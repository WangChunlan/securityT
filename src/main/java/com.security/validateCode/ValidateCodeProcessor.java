package com.security.validateCode;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName ValidateCodeProcessor
 * @Description TODO
 *          校验码处理器，封装不同校验码的处理逻辑
 * @Author wangchunlan
 * @Date 2018/8/20 16:58
 * @Version 1.0
 **/
public interface ValidateCodeProcessor {

    /**
     * @Author wangchunlan
     * @Description
     *          创建校验码
     * @Date 16:59 2018/8/20
     * @Param [request]
     * @return void
     **/
    void create(ServletWebRequest request) throws Exception;

    /**
     * @Author wangchunlan
     * @Description
     *          校验验证码
     * @Date 16:59 2018/8/20
     * @Param [request]
     * @return void
     **/
    void validate(ServletWebRequest request);
}
