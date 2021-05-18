package com.testing.controller.command.other;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.CommandUtil;
import com.testing.controller.util.PageResourceManager;
import com.testing.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(MainCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributesResourceManager.getProperty("parameter.user"));
        if (user!=null) {
            return CommandUtil.getUserPageByRole(user.getRole().getROLE());
        }
        LOGGER.info("Main command execute");
        return PageResourceManager.getProperty("redirect.login");
    }
}
