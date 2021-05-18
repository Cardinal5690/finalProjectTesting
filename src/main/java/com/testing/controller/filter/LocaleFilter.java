package com.testing.controller.filter;

import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.PageResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LocaleFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(LocaleFilter.class);
    private Map<String, Locale> languages = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        languages.put("ua",new Locale("ua"));
        languages.put("en",new Locale("en"));
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("Do locale filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String path = request.getRequestURI();
        String page = request.getHeader(PageResourceManager.getProperty("header"));
        if(path.contains("/language/")){
            String language = path.replaceAll(".*/language/", "");
            session.setAttribute(AttributesResourceManager.getProperty("parameter.language"), languages.get(language));
            response.sendRedirect(request.getContextPath() + page);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
