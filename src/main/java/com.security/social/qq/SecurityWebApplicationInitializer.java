package com.security.social.qq;

import com.security.conf.WebSecurityConf;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.SessionTrackingMode;
import java.util.EnumSet;
import java.util.Set;

@Order(1)
public class SecurityWebApplicationInitializer
        extends AbstractSecurityWebApplicationInitializer {
    @Override
    protected Set<SessionTrackingMode> getSessionTrackingModes() {
        return super.getSessionTrackingModes();
    }

    @Override
    protected EnumSet<DispatcherType> getSecurityDispatcherTypes() {
        return super.getSecurityDispatcherTypes();
    }
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { WebSecurityConf.class };
    }
//    @Order(2)
//    public class SecurityWebApplicationInitializer
//            extends AbstractSecurityWebApplicationInitializer {
//    }
}
