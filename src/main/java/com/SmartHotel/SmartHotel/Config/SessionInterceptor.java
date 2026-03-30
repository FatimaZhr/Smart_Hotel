package com.SmartHotel.SmartHotel.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

         String path = request.getRequestURI();
        if (path.equals("/login") ||
                path.equals("/logout") ||
                path.startsWith("/css") ||
                path.startsWith("/images")) {
            return true;
        }

        // واش المستخدم logged in؟
        Object user = request.getSession()
                .getAttribute("loggedUser");
        if (user == null) {
             response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}