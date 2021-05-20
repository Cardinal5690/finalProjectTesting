package com.testing.controller.command.other.admin;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.PageResourceManager;
import com.testing.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminCommandPage implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminCommandPage.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User admin = (User) request.getSession().getAttribute(AttributesResourceManager.getProperty("parameter.user"));

        request.setAttribute(AttributesResourceManager.getProperty("parameter.name"), admin.getName());
        request.setAttribute(AttributesResourceManager.getProperty("parameter.surname"), admin.getSurname());
        request.setAttribute(AttributesResourceManager.getProperty("parameter.email"), admin.getEmail());
        LOGGER.info("Admin command execute");
        return PageResourceManager.getProperty("page.admin");
    }
}
