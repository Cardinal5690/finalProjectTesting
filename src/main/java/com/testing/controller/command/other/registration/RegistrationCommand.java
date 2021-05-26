package com.testing.controller.command.other.registration;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.CommandUtil;
import com.testing.controller.util.PageResourceManager;
import com.testing.controller.util.ValidationUserExist;
import com.testing.model.entity.User;
import com.testing.model.entity.type.Role;
import com.testing.model.entity.type.Status;
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

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = new UserServiceImpl();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        ValidationUserExist validationUserExist = new ValidationUserExist();
        Validator validator = factory.getValidator();
        try {

            String name = request.getParameter(AttributesResourceManager.getProperty("parameter.name"));
            String surname = request.getParameter(AttributesResourceManager.getProperty("parameter.surname"));
            String email = request.getParameter(AttributesResourceManager.getProperty("parameter.email"));
            String password = request.getParameter(AttributesResourceManager.getProperty("parameter.password"));

            User user = new User(name, surname, email, password, Role.STUDENT, Status.UNBLOCKED);
            Set<ConstraintViolation<User>> constraintViolationSet = validator.validate(user);
            if (!constraintViolationSet.isEmpty()) {
                throw new WrongDataException();
            }
            if (!validationUserExist.userExist(email)) {
                throw new UserExistException();
            }
            userService.create(user);
            return PageResourceManager.getProperty("page.login");
        } catch (WrongDataException e) {
            LOGGER.error(e);
            request.setAttribute(AttributesResourceManager.getProperty("parameter.error.registration"), true);
        } catch (UserExistException e) {
            LOGGER.error(e);
            request.setAttribute(AttributesResourceManager.getProperty("parameter.error.user.exist"), true);
        }
        return new RegistrationCommandPage().execute(request, response);
    }
}

