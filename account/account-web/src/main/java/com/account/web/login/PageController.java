package com.account.web.login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
    @Value("${info.account.login.url}")
    private String loginUrl;

    @RequestMapping("/login/index")
    public ModelAndView loginIndex(@RequestParam(required = false) String redirect) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("login/index");
        modelAndView.addObject("redirect",redirect);
        modelAndView.addObject("loginUrl",loginUrl.replace("/index",""));
        return modelAndView;
    }
}
