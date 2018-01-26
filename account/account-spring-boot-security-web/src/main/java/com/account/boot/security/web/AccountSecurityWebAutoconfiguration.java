package com.account.boot.security.web;

import com.account.boot.security.web.userdetails.CurrentAccountControllerAdvice;
import com.account.boot.security.web.userdetails.PermissionEvaluatorImpl;
import com.account.boot.security.web.userdetails.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;

/**
 *  security web auto configuration
 *
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class AccountSecurityWebAutoconfiguration {
    /**
     * remember me key for salt
     */
    public static String rememberMeAppKey = "ytx007";

    @Value("${info.domain}")
    private String domain;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(PermissionEvaluator.class)
    public PermissionEvaluator permissionEvaluator() {
        return new PermissionEvaluatorImpl();
    }

    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsService customUserDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(RememberMeServices.class)
    public RememberMeServices rememberMeServices() {
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(rememberMeAppKey, customUserDetailsService());
        rememberMeServices.setAlwaysRemember(true);
        rememberMeServices.setUseSecureCookie(false);
        rememberMeServices.setCookieDomain(domain);
        return rememberMeServices;
    }

    @Bean
    public CurrentAccountControllerAdvice currentAccountControllerAdvice() {
        return new CurrentAccountControllerAdvice();
    }

}
