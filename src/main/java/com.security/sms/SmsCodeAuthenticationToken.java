package com.security.sms;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

/**
 * @ClassName SmsCodeAuthenticationToken
 *
 *          复制 UsernamePasswordAuthenticationToken
 *          写一个类似的与UsernamePasswordAuthenticationToken 的工具来认证短信验证码
 *
 *
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/23 9:31
 * @Version 1.0
 **/
public class SmsCodeAuthenticationToken   extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 500L;
    private final Object principal;

    // SmsCodeAuthenticationFilter中构建的未认证的Authentication
    public SmsCodeAuthenticationToken(String mobile ) {
        super((Collection)null);
        this.principal = mobile;
        this.setAuthenticated(false);
    }
// SmsCodeAuthenticationProvider中构建已认证的 Authentication
    public SmsCodeAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    public void eraseCredentials() {
        super.eraseCredentials();
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
