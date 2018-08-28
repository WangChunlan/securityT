package com.security.newcode;

import com.security.details.MyUserDetails;
import com.security.handler.MyAuthenticationFailureHandler;
import com.security.handler.MyAuthenticationSuccessHandler;
import com.security.properties.MySecurityProperties;
import com.security.properties.SecurityConstants;
import com.security.sms.SmsCodeAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 *  重构图形验证码 短信验证码 之后的配置文件
 *   代码 除了SmsCodeAuthenticationSecurityConfig  是之前的代码以外，newcode这个包的代码都是新创建的
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private  MyAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private  MySecurityProperties securityProperty;
    @Autowired
    private  DataSource dataSource;
    @Autowired
    private  MyUserDetails myUserDetailService;
    @Autowired
    private  ValidateCodeFilter_new validateCodeFilter;
    @Autowired
    private  SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        http
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)//图片验证码过滤器
                .formLogin()//form登录以及登录失败和成功处理
                .loginPage(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .rememberMe()//记住我
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperty.getBrowser().getRememberMeSeconds())
                .userDetailsService(myUserDetailService)
                .and()
                .authorizeRequests()//请求过滤拦截
                .antMatchers(
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                        SecurityConstants.DEFAULT_IMAGECODE_PROCESSING_URL,
                        SecurityConstants.DEFAULT_SMSCODE_PROCESSING_URL
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()//禁用跨站安全
                .csrf().disable()
                .apply(smsCodeAuthenticationSecurityConfig);
    }



    //创建两个本地用户,管理员和普通用户,要从数据库取的话自行修改
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.inMemoryAuthentication()
//                .withUser("user").password("{MD5}e10adc3949ba59abbe56e057f20f883e").roles("USER")
//                .and()
//                .withUser("admin").password("{MD5}e10adc3949ba59abbe56e057f20f883e").roles("ADMIN");
//    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/css/**", "/static/js/**", "/static/images/**", "/static/fonts/**", "/static/favicon.ico");
    }
}
