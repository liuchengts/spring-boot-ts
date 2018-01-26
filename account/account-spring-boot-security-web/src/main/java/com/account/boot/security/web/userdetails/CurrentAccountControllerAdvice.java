package com.account.boot.security.web.userdetails;

import com.account.common.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * controller advice:  get current login account
 *
 */
@ControllerAdvice
public class CurrentAccountControllerAdvice {

    @Value("${info.account.logout.url}")
    private String logoutUrl;

    @Value("${info.account.login.url}")
    private String loginUrl;

    @Value("${info.domain.version}")
    private String version;

    @ModelAttribute("currentAccount")
    public Account getAccountAccount(Authentication authentication) {
        return (authentication == null) ? null : ((CurrentUserDetails) authentication.getPrincipal());
    }

    @ModelAttribute("logoutUrl")
    public String getLogoutUrl() {
        return logoutUrl;
    }

    @ModelAttribute("loginUrl")
    public String getLoginUrl() {
        return loginUrl;
    }

    @ModelAttribute("version")
    public String getVersion() {
        return version + "?t=" + System.currentTimeMillis();
    }

    @ModelAttribute("authorities")
    public List<String> getAuthorities(Authentication authentication) {
        if (authentication == null) {
            return new ArrayList<>();
        }
        CurrentUserDetails currentUserDetails = (CurrentUserDetails) authentication.getPrincipal();
        if (currentUserDetails == null || currentUserDetails.getAuthoritiesText() == null) {
            return new ArrayList<>();
        }
        //获取当前登录用户的权限
        return Arrays.stream(currentUserDetails.getAuthoritiesText().split(",")).collect(Collectors.toList());
    }
}
