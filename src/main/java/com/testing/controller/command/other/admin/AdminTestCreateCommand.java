package com.testing.controller.command.other.admin;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.model.entity.Subject;
import com.testing.model.entity.Test;
import com.testing.model.entity.type.Complexity;
import com.testing.model.exception.WrongDataException;
import com.testing.model.service.SubjectService;
import com.testing.model.service.TestService;
import com.testing.model.service.impl.SubjectServiceImpl;
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
    private final TestService testService = new TestServiceImpl();
    private final SubjectService subjectService = new SubjectServiceImpl();
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Admin creates test command");
        String testName = request.getParameter(AttributesResourceManager.getProperty("parameter.test.name"));
        String complexity = request.getParameter(AttributesResourceManager.getProperty("parameter.test.complexity"));
        String title = request.getParameter(AttributesResourceManager.getProperty("parameter.title"));
        request.setAttribute(AttributesResourceManager.getProperty("parameter.title"), title);
        try {
            int subjectId = Integer.parseInt(request.getParameter(AttributesResourceManager.getProperty("parameter.subject.id")));
            request.setAttribute(AttributesResourceManager.getProperty("parameter.subject.id"), subjectId);
            Subject subject = subjectService.findByTitle(title);
            int time = Integer.parseInt(request.getParameter(AttributesResourceManager.getProperty("parameter.test.time")));
            Complexity testComplexity = Enum.valueOf(Complexity.class, complexity);
            Test test = new Test(testName,time,testComplexity,subject.getId());
            Set<ConstraintViolation<Test>> constraintViolationSet = validator.validate(test);
            if (!constraintViolationSet.isEmpty()) {
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
