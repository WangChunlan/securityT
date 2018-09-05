package com.security.conf;

import com.security.details.MyUserDetails;
import com.security.filter.ValidateCodeFilter;
import com.security.handler.MyAuthenticationFailureHandler;
import com.security.handler.MyAuthenticationSuccessHandler;
import com.security.session.ExpiredSessionStrategy;
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
import javax.websocket.Session;

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
    private SpringSocialConfigurer mySocialSecurityConfig; // 向当前的过滤器链上添加一个过滤器

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
                .apply(mySocialSecurityConfig)   // qq
                .and() // remember-me
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60*60*24*7)
                .userDetailsService(myUserDetails)



                // session 并发登录   +++ session失效 跳转
                .and()
                .sessionManagement()
                .invalidSessionUrl("/session/invalid")       // session失效时，要跳转
                .maximumSessions(1)                            // 最大session数量
                .expiredSessionStrategy(new ExpiredSessionStrategy())       // 详细记录谁把谁给踢掉
                .and()
                .and()



//                 退出登录
                .logout()
                .logoutUrl("/signOut")
//                .logoutSuccessUrl("/login") // 退出后定向到指定界面  todo 并没有跳转 到login   是不是退出后，session失效 便跳转到？？？  .invalidSessionUrl("/session/invalid")
//                .logoutSuccessHandler() todo!!!!!!!!!!!!!!!!!!!
                .deleteCookies("JSESSIONID")  // 删除cookie
                .and()


                .authorizeRequests()
                .antMatchers("/login/**", SecurityConstants.DEFAULT_IMAGECODE_PROCESSING_URL,
                        SecurityConstants.DEFAULT_SMSCODE_PROCESSING_URL,"/regist","/demoSignUp","/signUp","/session/invalid").permitAll()
                .anyRequest()
                .authenticated() ;

                http.sessionManagement().maximumSessions(1).expiredUrl("/session/invalid");

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
