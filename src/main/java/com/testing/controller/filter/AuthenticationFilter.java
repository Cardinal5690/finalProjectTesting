package com.testing.controller.filter;

import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.PageResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("doFilter");
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession();
        String loginURI = request.getContextPath() + PageResourceManager.getProperty("key.login");
        String registrationURI = request.getContextPath() + PageResourceManager.getProperty("key.registration");

        boolean loggedIn = session != null && session.getAttribute(AttributesResourceManager.getProperty("parameter.user")) != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean signUpRequest = request.getRequestURI().equals(registrationURI);
        if (loggedIn && (loginRequest || signUpRequest)) {
            request.getRequestDispatcher("/testing/main").forward(request, response);
        } else if (loggedIn || loginRequest || signUpRequest) {
            filterChain.doFilter(request, response);
        } else if (request.getRequestURI().equals(PageResourceManager.getProperty("key.registration"))) {
            LOGGER.info("Registration Forward");
            request.getRequestDispatcher(PageResourceManager.getProperty("page.registration")).forward(request, response);
        } else if (request.getRequestURI().equals(PageResourceManager.getProperty("key.login"))) {
            LOGGER.info("Login Forward");
            request.getRequestDispatcher(PageResourceManager.getProperty("page.login")).forward(request, response);
        } else {
            response.sendRedirect(PageResourceManager.getProperty("key.login"));
        }
    }

    @Override
    public void destroy() {
    }
}
