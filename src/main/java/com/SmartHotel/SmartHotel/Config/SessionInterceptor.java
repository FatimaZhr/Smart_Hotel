package com.SmartHotel.SmartHotel.Config;

import com.SmartHotel.SmartHotel.Enteties.User;
import com.SmartHotel.SmartHotel.Enums.Role;
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
                path.equals("/register") ||
                path.equals("/logout") ||
                path.startsWith("/css") ||
                path.startsWith("/images")) {
            return true;
        }

        // واش المستخدم logged in؟
        User user = (User) request.getSession()
                .getAttribute("loggedUser");
        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }

        if (path.startsWith("/dashboard") &&
                user.getRole() != Role.MANAGER) {
            response.sendRedirect("/rooms");
            return false;
        }
        return true;
    }


}