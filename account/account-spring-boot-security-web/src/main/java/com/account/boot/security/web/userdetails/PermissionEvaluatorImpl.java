package com.account.boot.security.web.userdetails;

import com.account.common.Account;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * user details service implementation
 *
 */
public class PermissionEvaluatorImpl implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object domainObject, Object permission) {

        Account account = ((CurrentUserDetails) authentication.getPrincipal());
        if (account == null) {
            return false;
        }
        //获取当前登录用户的权限
        List<String> sourceList = Arrays.stream(account.getAuthoritiesText().split(",")).collect(Collectors.toList());
        //不区分大小写
        return sourceList.stream().anyMatch(source -> source.toUpperCase().equals(permission));
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}


