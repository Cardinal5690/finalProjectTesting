package com.testing.controller.command.other;

import com.testing.controller.command.Command;
import com.testing.controller.util.PageResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageErrorCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(PageErrorCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("execute");
        return PageResourceManager.getProperty("page.not.found");
    }
}
