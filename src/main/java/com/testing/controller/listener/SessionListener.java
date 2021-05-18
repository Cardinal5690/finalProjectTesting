package com.testing.controller.listener;

import com.testing.controller.util.AttributesResourceManager;
import com.testing.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        User user = (User) session.getAttribute(AttributesResourceManager.getProperty("parameter.user"));
        ServletContext servletContext = session.getServletContext();
        Map<User, HttpSession> loggedUsers =
                (HashMap<User, HttpSession>) servletContext.getAttribute(
                        AttributesResourceManager.getProperty("attribute.servlet.context.logged.users"));
        loggedUsers.remove(user);
    }
}
