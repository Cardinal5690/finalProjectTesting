package com.testing.controller.listener;

import com.testing.controller.util.AttributesResourceManager;
import com.testing.model.entity.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().addListener(new SessionListener());
        servletContextEvent.getServletContext().setAttribute(
                AttributesResourceManager.getProperty("attribute.servlet.context.logged.users"),
                new HashMap<User, HttpSession>());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
