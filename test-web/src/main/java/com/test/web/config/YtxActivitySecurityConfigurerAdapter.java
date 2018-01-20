package com.test.web.config;

import com.account.boot.security.mobile.YtxMobileWebSecurityConfigurerAdapter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * web security config
 *
 * @author linux_china
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class YtxActivitySecurityConfigurerAdapter extends YtxMobileWebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(whiteUrls).permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .rememberMe().key(rememberMeAppKey);
        internalConfigure(http);
    }

    @Override
    protected void internalConfigure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers(whiteUrls).permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(whiteUrls);
    }
}