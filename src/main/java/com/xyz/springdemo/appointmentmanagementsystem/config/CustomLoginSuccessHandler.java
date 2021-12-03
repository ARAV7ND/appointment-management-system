package com.xyz.springdemo.appointmentmanagementsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Configuration
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
@Override
public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    Authentication authentication) throws IOException, ServletException {

    Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

    if (roles.contains("ROLE_ADMIN")) {
        httpServletResponse.sendRedirect("/admin/home");
    } else if(roles.contains("ROLE_DOCTOR")){
        httpServletResponse.sendRedirect("/doctor/home");
    }else{
        httpServletResponse.sendRedirect("/patient/home");
    }
}


}
