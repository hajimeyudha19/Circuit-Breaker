package com.ensat.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class RedirectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getScheme().equals("http")) {
            String httpsURL = "https://" + request.getServerName() + request.getRequestURI();
            if (request.getQueryString() != null) {
                httpsURL += "?" + request.getQueryString();
            }
            response.sendRedirect(httpsURL);
            return false;
        }
        return true;
    }
}
