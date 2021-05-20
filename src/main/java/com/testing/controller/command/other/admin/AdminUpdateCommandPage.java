package com.testing.controller.command.other.admin;

import com.testing.controller.command.Command;
import com.testing.controller.util.PageResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminUpdateCommandPage implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminUpdateCommandPage.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Execute update page");
        return PageResourceManager.getProperty("page.admin.update");
    }
}
