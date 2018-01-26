package com.account.web.login;

import com.account.boot.security.web.userdetails.CurrentUserDetails;
import com.account.cloud.starter.client.AccountClient;
import com.account.common.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    /**
     * 设置cookie的作用域名
     */
    @Value("${info.domain}")
    private String domain;
    @Value("${info.buyer.domain}")
    private String buyerDomain;
    @Value("${info.seller.domain}")
    private String sellerDomain;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private RememberMeServices rememberMeServices;

    @GetMapping("/logint")
    public Map logint(@RequestParam String username,
                     @RequestParam String password,
                     @RequestParam(required = false) String redirect,
                     @RequestParam(required = false) String captcha,
                     @CookieValue(required = false) String cid,
                     HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        //1、验证码验证
        //2、账号密码验证
        Account account = accountClient.findAccount(username, password);
        if (null != account) {
            handleLogin(account, redirect, request, response, result);
        } else {
            result.put("error", "用户名或密码错误");
        }
        return result;
    }
    @PostMapping("/login")
    public Map login(@RequestParam String username,
                     @RequestParam String password,
                     @RequestParam(required = false) String redirect,
                     @RequestParam(required = false) String captcha,
                     @CookieValue(required = false) String cid,
                     HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        //1、验证码验证
        //2、账号密码验证
        Account account = accountClient.findAccount(username, password);
        if (null != account) {
            handleLogin(account, redirect, request, response, result);
        } else {
            result.put("error", "用户名或密码错误");
        }
        return result;
    }

    private void handleLogin(Account account, String redirect, HttpServletRequest request, HttpServletResponse response, Map<String, Object> result) {
        try {
            CurrentUserDetails currentUserDetails = new CurrentUserDetails(account);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(currentUserDetails, currentUserDetails.getPassword(), currentUserDetails.getAuthorities());
            HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response) {
                @Override
                public void addCookie(Cookie cookie) {
                    if (cookie.getDomain() == null) {
                        cookie.setDomain(domain);
                    }
                    cookie.setPath("/");
                    super.addCookie(cookie);
                }
            };
            rememberMeServices.loginSuccess(request, wrapper, authentication);
            //通过账户获得用户的信息
            wrapper.addCookie(new Cookie("nickname", URLEncoder.encode(account.getAccountName(), "UTF-8")));
//            wrapper.addCookie(new Cookie("headImg", account.getHeadImg() == null ? "" : account.getHeadImg()));
            result.put("result", true);
            if (account.getAuthoritiesText().contains("ROLE_BUYER")) {
                wrapper.addCookie(new Cookie("accountType", "1"));
            } else if (account.getAuthoritiesText().contains("ROLE_SELLER")) {
                wrapper.addCookie(new Cookie("accountType", "2"));
            }
            if (StringUtils.isEmpty(redirect)) {
                if (account.getAuthoritiesText().contains("ROLE_BUYER")) {
                    result.put("redirect", buyerDomain);
                } else if (account.getAuthoritiesText().contains("ROLE_SELLER")) {
                    result.put("redirect", sellerDomain);
                } else {
                    result.put("redirect", "http://" + domain);
                }
            } else {
                redirect = URLDecoder.decode(redirect, "UTF-8");
                result.put("redirect", redirect);
            }
        } catch (UnsupportedEncodingException e) {
            log.error("编解码出错", e.getCause());
        }

    }
}
