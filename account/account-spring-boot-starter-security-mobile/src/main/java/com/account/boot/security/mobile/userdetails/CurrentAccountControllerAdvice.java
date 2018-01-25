package com.account.boot.security.mobile.userdetails;

import com.account.common.Account;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * controller advice:  get current login account
 *
 * @author linux_china
 */
@ControllerAdvice
public class CurrentAccountControllerAdvice {

    @ModelAttribute("currentAccount")
    public Account getAccountAccount(Authentication authentication) {
        return (authentication == null) ? null : ((CurrentUserDetails) authentication.getPrincipal());
    }

}
