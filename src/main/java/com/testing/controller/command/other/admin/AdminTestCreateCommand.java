package com.testing.controller.command.other.admin;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.CommandUtil;
import com.testing.controller.util.PageResourceManager;
import com.testing.model.entity.Test;
import com.testing.model.entity.User;
import com.testing.model.entity.type.Complexity;
import com.testing.model.exception.WrongDataException;
import com.testing.model.service.TestService;
import com.testing.model.service.impl.TestServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Set;

public class AdminTestCreateCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminTestCreateCommand.class);
    private TestService testService = new TestServiceImpl();
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Execute");
        String testName = request.getParameter(AttributesResourceManager.getProperty("parameter.test.name"));
        String complexity = request.getParameter(AttributesResourceManager.getProperty("parameter.test.complexity"));
        request.setAttribute(AttributesResourceManager.getProperty("parameter.title"), request.getParameter(AttributesResourceManager.getProperty("parameter.title")));
        try {
            int subjectId = Integer.parseInt(request.getParameter(AttributesResourceManager.getProperty("parameter.subject.id")));
            request.setAttribute(AttributesResourceManager.getProperty("parameter.subject.id"), subjectId);
            int time = Integer.parseInt(request.getParameter(AttributesResourceManager.getProperty("parameter.test.time")));
            Complexity testComplexity = Enum.valueOf(Complexity.class, complexity);
            Test test = new Test(testName,time,testComplexity,subjectId);
            Set<ConstraintViolation<Test>> constraintViolationSet = validator.validate(test);
            if (constraintViolationSet.size() > 0) {
                throw new WrongDataException("Incorrect test data");
            }
            testService.create(test);
        } catch (WrongDataException | IllegalArgumentException e){
            LOGGER.error(e);
            request.setAttribute(AttributesResourceManager.getProperty("parameter.error.create"), true);
        }
        return new AdminTestCreateCommandPage().execute(request,response);
    }
}
