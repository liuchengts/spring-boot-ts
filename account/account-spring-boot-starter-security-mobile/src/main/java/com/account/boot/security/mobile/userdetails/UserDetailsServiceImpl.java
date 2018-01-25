package com.account.boot.security.mobile.userdetails;
import com.account.cloud.starter.client.AccountClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * user details service implementation
 *
 * @author linux_china
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private  AccountClient accountClient;

    public CurrentUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CurrentUserDetails(accountClient.findOneAccount(username));
    }
}
