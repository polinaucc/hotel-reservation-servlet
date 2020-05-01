package ua.polina.controller.filter;

import ua.polina.controller.config.SecurityConfig;
import ua.polina.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("principals");

        String url = request.getRequestURI();
        url = url.replace("/", "");
        System.out.println("URL: " + url);
        if(!SecurityConfig.isSecured(url)){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if(user==null){
            if(url.equals("/login")){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        if(SecurityConfig.isAccessAllowed(url, user.getAuthorities())){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            response.sendRedirect("/ok.jsp");
        }
    }

}
