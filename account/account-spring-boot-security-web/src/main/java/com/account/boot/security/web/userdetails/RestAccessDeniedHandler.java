package com.account.boot.security.web.userdetails;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RestAccessDeniedHandler extends AccessDeniedHandlerImpl {
    private String loginUrl;

    private String redirect;

    private String buyerCenterUrl;

    private String sellerCenterUrl;

    public RestAccessDeniedHandler(String loginUrl, String redirect) {
        this.loginUrl = loginUrl;
        this.redirect = redirect;
    }

    public RestAccessDeniedHandler(String loginUrl, String redirect, String buyerCenterUrl, String sellerCenterUrl) {
        this.loginUrl = loginUrl;
        this.redirect = redirect;
        this.buyerCenterUrl = buyerCenterUrl;
        this.sellerCenterUrl = sellerCenterUrl;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String returnUrl = request.getRequestURL().toString();
        Map<String, String[]> params = request.getParameterMap();
        String queryString = "";
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (String value : values) {
                queryString += key + "=" + value + "&";
            }
        }
        // 去掉最后一个空格
        if (StringUtils.isNotEmpty(queryString)) {
            queryString = queryString.substring(0, queryString.length() - 1);
            returnUrl = returnUrl + "?" + queryString;
        }
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "ACCESS DENIED");
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
}
