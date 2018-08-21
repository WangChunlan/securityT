package com.security.validateCode;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName ValidateCode
 * @Description 验证码信息封装类
 * @Author wangchunlan
 * @Date 2018/8/20 16:43
 * @Version 1.0
 **/
public class ValidateCode implements Serializable {

    private static final long serialVersionUID = 428447863857238193L;
    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime){
        this.code = code;
        this.expireTime = expireTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }




    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }



}
