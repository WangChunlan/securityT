package com.security.properties;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SecurityCodeConfigure
 * @Description 扫描自己配置的属性类，到全局配置文件中
 *        TODO       默认扫到 application.properties文件中 可以自行配置扫描到那个文件中  暂时不知道那个注解
 * @Author wangchunlan
 * @Date 2018/8/20 11:01
 * @Version 1.0
 **/

@Configuration
@EnableConfigurationProperties({MySecurityProperties.class})
public class SecurityCodeConfigure {
}
