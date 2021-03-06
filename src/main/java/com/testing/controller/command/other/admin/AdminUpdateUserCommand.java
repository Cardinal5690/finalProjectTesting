package com.testing.controller.command.other.admin;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.PageResourceManager;
import com.testing.controller.util.ValidationUserExist;
import com.testing.model.entity.User;
import com.testing.model.exception.UserExistException;
import com.testing.model.exception.WrongDataException;
import com.testing.model.service.UserService;
import com.testing.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class AdminUpdateUserCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminUpdateUserCommand.class);
    private final UserService userService = new UserServiceImpl();
    private final ValidationUserExist validationUserExist = new ValidationUserExist();
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Execute update user");
        String userId = request.getParameter(AttributesResourceManager.getProperty("parameter.user.id"));
        if(userId==null|| userId.isEmpty()){
            return PageResourceManager.getProperty("redirect.admin");
        }
        String userName =
                request.getParameter(AttributesResourceManager.getProperty("parameter.user.name"));
        String userSurname =
                request.getParameter(AttributesResourceManager.getProperty("parameter.user.surname"));
        String userEmail =
                request.getParameter(AttributesResourceManager.getProperty("parameter.user.email"));
        String userPassword =
                request.getParameter(AttributesResourceManager.getProperty("parameter.user.password"));
        String userStatus =
                request.getParameter(AttributesResourceManager.getProperty("parameter.user.status"));

        int userIdParseToInt = Integer.parseInt(userId);
        User userFromDB = userService.findById(userIdParseToInt);

        try {
            if (userFromDB == null) {
                throw new WrongDataException("Incorrect user id");
            }
            if (!validationUserExist.userExist(userEmail)) {
                throw new UserExistException("Email must be unique");
            }
            userService.setNewParameterUser(userFromDB, userName, userSurname, userEmail, userPassword, userStatus);
            Set<ConstraintViolation<User>> constraintViolationSet = validator.validate(userFromDB);
            if (!constraintViolationSet.isEmpty()) {
                throw new WrongDataException("Incorrect user data");
            }
            userService.update(userFromDB);
            return PageResourceManager.getProperty("redirect.admin");
        } catch (WrongDataException e) {
            LOGGER.error(e);
            request.setAttribute(AttributesResourceManager.getProperty("parameter.error.update"), true);
        } catch (UserExistException e) {
            LOGGER.error(e);
            request.setAttribute(AttributesResourceManager.getProperty("parameter.error.user.exist"), true);
        }
        return new AdminCommandPage().execute(request, response);
    }
}
