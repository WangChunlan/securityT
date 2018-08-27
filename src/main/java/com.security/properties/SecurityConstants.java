package com.security.properties;

/**
 * @ClassName SecurityConstants
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/27 11:38
 * @Version 1.0
 **/
public interface SecurityConstants {
    /**
     * form表单中
     * 默认的用户名密码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/login";
    /**
     * form表单中
     * 默认的手机验证码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/login/mobile";
    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
    /**
     * 短信验证码，session key
     */
    public static final String DEFAULT_SESSION_KEY_FOR_CODE_SMS = "SESSION_KEY_SMS_CODE";
    /**
     * 图形验证码，session key
     */
    public static final String DEFAULT_SESSION_KEY_FOR_CODE_IMAGE = "SESSION_KEY_IMAGE_CODE";
    /**
     * 图片验证码请求处理url
     */
    public static final String DEFAULT_IMAGECODE_PROCESSING_URL = "/code/image";
    /**
     * 手机验证码请求处理url
     */
    public static final String DEFAULT_SMSCODE_PROCESSING_URL = "/code/sms";

}
