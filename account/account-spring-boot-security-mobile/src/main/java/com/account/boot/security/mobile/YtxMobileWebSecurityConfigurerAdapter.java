package com.account.boot.security.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

/**
 * mobile security basic configurer
 *
 * @author linux_china
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public abstract class YtxMobileWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Autowired
    private  UserDetailsService userDetailsService;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    /**
     * white urls for static resources
     */
    public String[] whiteUrls = new String[]{"/webjars/**", "/webjars/jsondoc-ui-webjar/**", "/static/**", "/static/css/**", "/static/images/**", "/static/js/**", "/jsondoc", "/jsondoc-ui.html", "**/*.css", "**/*.js", "**/*.map", "*.html", "/health", "/metrics"};

    /**
     * remember me key for salt
     */
    public static String rememberMeAppKey = "ytx007";

    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new AuthorizationHeaderFilter(), RememberMeAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(whiteUrls).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .httpBasic().disable().anonymous().disable()
                .rememberMe().key(rememberMeAppKey)
                .and()
                .exceptionHandling().accessDeniedPage("/403");
        internalConfigure(http);
    }

    /**
     * internal configure for http security, main: antMatchers("/home").authenticated()
     *
     * @param http http security
     * @throws Exception
     */
    protected abstract void internalConfigure(HttpSecurity http) throws Exception;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
