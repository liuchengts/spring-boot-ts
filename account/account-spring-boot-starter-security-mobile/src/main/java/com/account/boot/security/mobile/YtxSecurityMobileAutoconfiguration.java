package com.account.boot.security.mobile;

import com.account.boot.security.mobile.userdetails.CurrentAccountControllerAdvice;
import com.account.boot.security.mobile.userdetails.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * ytx security mobile auto configuration
 *
 * @author linux_china
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class YtxSecurityMobileAutoconfiguration {
    /**
     * remember me key for salt
     */
    public static String rememberMeAppKey = "ytx007";
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsService customUserDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public CurrentAccountControllerAdvice currentAccountControllerAdvice() {
        return new CurrentAccountControllerAdvice();
    }
    @Bean
    @ConditionalOnMissingBean(RememberMeServices.class)
    public RememberMeServices rememberMeServices() {
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(rememberMeAppKey, customUserDetailsService());
        rememberMeServices.setAlwaysRemember(true);
        rememberMeServices.setUseSecureCookie(false);
        return rememberMeServices;
    }

}
