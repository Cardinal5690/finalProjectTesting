package com.testing.controller.command.other.login;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.CommandUtil;
import com.testing.controller.util.ContextUtil;
import com.testing.controller.util.PageResourceManager;
import com.testing.model.entity.User;
import com.testing.model.entity.type.Status;
import com.testing.model.service.UserService;
import com.testing.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter(AttributesResourceManager.getProperty("parameter.login"));
        String password = request.getParameter(AttributesResourceManager.getProperty("parameter.password"));

        if (login != null && password != null) {
            UserService userService = new UserServiceImpl();
            User user = userService.findUserByLoginAndPassword(login, password);
            if (ContextUtil.isUserInContext(request.getSession(), user)) {
                ContextUtil.logoutUser(user);
            }
            request.getSession().setAttribute(AttributesResourceManager.getProperty("parameter.user"), user);
            ContextUtil.setAttributesToContext(request.getSession(), user);
            LOGGER.info("User login");
            if (user != null && user.getStatus().equals(Status.UNBLOCKED)) {
                return CommandUtil.getUserPageByRole(user.getRole().getROLE());
            } else {
                request.setAttribute(AttributesResourceManager.getProperty("parameter.not.found"), true);
            }
        }
        return PageResourceManager.getProperty("page.login");
    }
}
