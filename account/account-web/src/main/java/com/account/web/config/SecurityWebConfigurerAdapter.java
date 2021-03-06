package com.account.web.config;

import com.account.boot.security.web.userdetails.AccountWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.RememberMeServices;

/**
 * web security config
 *
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityWebConfigurerAdapter extends AccountWebSecurityConfigurerAdapter {

    @Autowired
    private RememberMeServices rememberMeServices;

    private String[] whiteAccountUrls = new String[]{"/**", "/"};
    @Value("${info.account.login.url}")
    private String loginUrl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(whiteAccountUrls).permitAll()
                .and()
                .rememberMe().key(rememberMeAppKey).rememberMeServices(rememberMeServices)
                .and()
                .exceptionHandling().authenticationEntryPoint(myLoginUrlAuthenticationEntryPoint())
                .accessDeniedHandler(restAccessDeniedHandler());
    }

    @Override
    protected void internalConfigure(HttpSecurity http) throws Exception {

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(whiteUrls).antMatchers("/password/**");
    }

    @Override
    protected String getRedirect() {
        return loginUrl;
    }
}