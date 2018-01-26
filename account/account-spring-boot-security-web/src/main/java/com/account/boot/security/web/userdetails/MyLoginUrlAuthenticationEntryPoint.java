package com.account.boot.security.web.userdetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.util.URLEncoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private String loginUrl;

    private String redirect;

    public MyLoginUrlAuthenticationEntryPoint(String loginFormUrl, String loginUrl, String redirect) {
        super(loginFormUrl);
        this.loginUrl = loginUrl;
        this.redirect = redirect;
    }

    public void commence(HttpServletRequest request,
                         HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        String returnUrl = request.getRequestURL().toString();
        Map<String, String[]> params = request.getParameterMap();
        String queryString = "";
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                queryString += key + "=" + value + "&";
            }
        }
        // 去掉最后一个空格
        if (StringUtils.isNotEmpty(queryString)) {
            queryString = queryString.substring(0, queryString.length() - 1);
            returnUrl = returnUrl + "?" + queryString;
        }
        //判断登录是否包含重定向
        String redirect = request.getParameter("redirect");
        if (StringUtils.isNotEmpty(redirect)) {
            returnUrl = redirect;
        }
        //如果强制定义跳转,以强制跳转为准,适用于卖家,卖家,平台等.
        if (StringUtils.isNotEmpty(this.redirect)) {
            returnUrl = this.redirect;
        }
        URLEncoder urlEncoder = new URLEncoder();
        returnUrl = urlEncoder.encode(returnUrl);

        String ajax = request.getParameter("ajax");
        if (ajax != null && "ok".equals(ajax)) {
            //未登录情况，则返回未登录的标志
            Map result = new HashMap();
            result.put("success", "unLogin");
            result.put("redirectUrl", loginUrl + "?redirect=" + returnUrl);
            ObjectMapper objectMapper = new ObjectMapper();

            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(result));
        } else {
            response.sendRedirect(loginUrl + "?redirect=" + returnUrl);
        }

//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
