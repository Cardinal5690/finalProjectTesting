package com.testing.controller.filter;

import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.CommandUtil;
import com.testing.controller.util.PageResourceManager;
import com.testing.model.entity.User;
import com.testing.model.entity.type.Role;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("Do authorization role filter");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute(AttributesResourceManager.getProperty("parameter.user"));
        String path = req.getRequestURI();
        if (path.contains("admin")) {
            if (user.getRole().equals(Role.ADMIN)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpResponse.sendRedirect(PageResourceManager.getProperty(CommandUtil.getUserPageByRole(user.getRole().getROLE())));
            }
        } else if (path.contains("student")) {
            if (user.getEmail()!=null && user.getRole().equals(Role.STUDENT)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpResponse.sendRedirect(PageResourceManager.getProperty(CommandUtil.getUserPageByRole(user.getRole().getROLE())));
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
