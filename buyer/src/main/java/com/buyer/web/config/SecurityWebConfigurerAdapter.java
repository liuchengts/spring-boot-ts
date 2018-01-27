package com.buyer.web.config;

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

    @Value("${info.seller.domain}")
    private String sellerDomain;
    @Override
    protected void internalConfigure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/**","/").hasAuthority("ROLE_BUYER")
                .anyRequest().authenticated();
    }

    public void configure(WebSecurity web) throws Exception {
        web.expressionHandler(myWebSecurityExpressionHandler());
        web.ignoring().antMatchers(whiteUrls);
    }

    @Override
    protected String getRedirect() {
        return null;
    }
}