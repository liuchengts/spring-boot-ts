package com.account.boot.security.mobile.userdetails;

import com.account.common.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * current user details
 *
 * @author linux_china
 */
public class CurrentUserDetails extends Account implements UserDetails {
    private List<GrantedAuthority> grantedAuthorities;

    public CurrentUserDetails(Account account) {
        setId(account.getId());
        setAccountName(account.getAccountName());
        setAccountPwd(account.getAccountPwd());
        setState(account.getState());
        setAuthoritiesText(account.getAuthoritiesText());
        this.grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(account.getAuthoritiesText());

    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.getAccountPwd();
    }

    public String getUsername() {
        return this.getAccountName();
    }


    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
