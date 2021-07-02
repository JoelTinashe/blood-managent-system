package com.digitalkrapht.bloodbank.bloodbank.security.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {


        if (e.getMessage().equalsIgnoreCase("Bad Credentials")) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Incorrect credentials.");
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Sorry, You're not authorized to access this resource.");
        }
    }
}
