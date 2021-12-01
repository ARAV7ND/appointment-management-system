package com.xyz.springdemo.appointmentmanagementsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String url = determineTargetUrl(authentication);
        if(response.isCommitted()){
            return;
        }
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request,response,url);
    }

    protected String determineTargetUrl(Authentication authentication) {
        String url = "/login?error=true";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();
        for(GrantedAuthority authority : authorities){
            roles.add(authority.getAuthority());
        }
        if(roles.contains("ROLE_ADMIN")){
            url = "/admin/home";
        }else if(roles.contains("ROLE_DOCTOR")){
            url = "/doctor/home";
        }else if(roles.contains("ROLE_USER")){
            url = "/patient/home";
        }

        return url;
    }
}
