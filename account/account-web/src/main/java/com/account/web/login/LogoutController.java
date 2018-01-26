package com.account.web.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 退出
 */
@Slf4j
@Controller
public class LogoutController {
    @Value("${info.domain}")
    private String domain;

    @Value("${info.account.login.url}")
    private String loginUrl;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logoutPage(HttpServletRequest request, HttpServletResponse response) {
        //清空cookie
        cancelCookie("remember-me",response);
        cancelCookie("visitorCode",response);
        cancelCookie("nickname",response);
        cancelCookie("headImg",response);
        //cancelCookie("visitFrom",response);
        //cancelCookie("visitorUniqueCode",response);
        return new ModelAndView("redirect:"+loginUrl);
    }

    private void cancelCookie(String cookieName, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }
}
