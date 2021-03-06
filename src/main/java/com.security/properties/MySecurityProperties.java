package com.security.properties;

import com.security.social.qq.properties.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName MySecurityProperties
 * @Description  配置application中的扫描前缀属性
 *         @ConfigurationProperties         只扫描 以 com.security 的属性
 * @Author wangchunlan
 * @Date 2018/8/20 10:54
 * @Version 1.0
 **/



@ConfigurationProperties(prefix = "com.security")
public class MySecurityProperties {

    private ValidateCodeProperties code=new ValidateCodeProperties();
    private BrowserProperty browser = new BrowserProperty();
    private SocialProperties social=new SocialProperties(); // 导入自己的包

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public BrowserProperty getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperty browser) {
        this.browser = browser;
    }
}
