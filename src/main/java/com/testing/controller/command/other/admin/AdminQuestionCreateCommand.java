package com.testing.controller.command.other.admin;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.model.entity.Question;
import com.testing.model.exception.WrongDataException;
import com.testing.model.service.QuestionService;
import com.testing.model.service.impl.QuestionServiceImpl;
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

public class AdminQuestionCreateCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminQuestionCreateCommand.class);
    private final QuestionService questionService = new QuestionServiceImpl();
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       LOGGER.info("Execute question create command (action)");
       String subjectId = request.getParameter(AttributesResourceManager.getProperty("parameter.subject.id"));
       String subjectTitle= request.getParameter(AttributesResourceManager.getProperty("parameter.title"));
       request.setAttribute(AttributesResourceManager.getProperty("parameter.subject.id"),subjectId);
       request.setAttribute(AttributesResourceManager.getProperty("parameter.title"),subjectTitle);
       try{
           int testId = Integer.parseInt(request.getParameter(AttributesResourceManager.getProperty("parameter.test.id")));
           request.setAttribute(AttributesResourceManager.getProperty("parameter.test.id"),testId);
           String questionText = request.getParameter(AttributesResourceManager.getProperty("parameter.question"));
           String correctAnswer = request.getParameter(AttributesResourceManager.getProperty("parameter.answer"));
           Question question = new Question(questionText,correctAnswer,testId);
           Set<ConstraintViolation<Question>> constraintViolationSet = validator.validate(question);
           if (!constraintViolationSet.isEmpty()) {
               throw new WrongDataException("Incorrect test data");
           }
           questionService.create(question);
       } catch (WrongDataException  e){
           LOGGER.error(e);
           request.setAttribute(AttributesResourceManager.getProperty("parameter.error.create"), true);
       }
        return new AdminQuestionCreateCommandPage().execute(request,response);
    }
}
