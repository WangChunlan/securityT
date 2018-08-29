package com.security.conf;

import com.security.details.MyUserDetails;
import com.security.filter.ValidateCodeFilter;
import com.security.handler.MyAuthenticationFailureHandler;
import com.security.handler.MyAuthenticationSuccessHandler;
import com.security.sms.SmsCodeAuthenticationSecurityConfig;
import com.security.utils.SecurityConstants;
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
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @Author wangchunlan
 * @Description
 * @Date 13:15 2018/8/20
 * @Param
 * @return
 **/
@Configuration
public class WebSecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;
    @Autowired
    private MyAuthenticationFailureHandler faileHandler;
    @Autowired
    private MyUserDetails myUserDetails;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer socialConfigurer;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);   // 仅仅第一次是可以用的
        return  tokenRepository;

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter =new ValidateCodeFilter();
        validateCodeFilter.setFailureHandler(faileHandler);

        http
                .addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler)
                .failureHandler(faileHandler)

                .and()
                .authorizeRequests()
                .antMatchers("/login/**", SecurityConstants.DEFAULT_IMAGECODE_PROCESSING_URL, SecurityConstants.DEFAULT_SMSCODE_PROCESSING_URL).permitAll()
                .anyRequest()
                .authenticated()
                // todo 1 qq
                                .and()
                .apply(socialConfigurer)   // qq

                // remember-me
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60*60*24*7)
                .userDetailsService(myUserDetails)
 ;


              http .csrf().disable()
                      .apply(smsCodeAuthenticationSecurityConfig);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/css/**", "/static/js/**", "/static/images/**", "/static/fonts/**", "/static/favicon.ico");
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
