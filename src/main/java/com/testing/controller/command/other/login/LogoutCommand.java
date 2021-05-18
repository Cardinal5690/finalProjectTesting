package com.testing.controller.command.other.login;

import com.testing.controller.command.Command;
import com.testing.controller.util.PageResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        LOGGER.info("Command Log out");
        return PageResourceManager.getProperty("redirect.login");
    }
}
