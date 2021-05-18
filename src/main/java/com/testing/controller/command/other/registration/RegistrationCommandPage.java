package com.testing.controller.command.other.registration;

import com.testing.controller.command.Command;
import com.testing.controller.command.other.MainCommand;
import com.testing.controller.util.PageResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommandPage implements Command {
    private static final Logger LOGGER = Logger.getLogger(RegistrationCommandPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("execute");
        return PageResourceManager.getProperty("page.registration");
    }
}
