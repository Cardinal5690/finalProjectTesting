package com.testing.controller.command.other.student;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.PageResourceManager;
import com.testing.model.entity.Question;
import com.testing.model.service.QuestionService;
import com.testing.model.service.impl.QuestionServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PassCommandPage implements Command {
    private static final Logger LOGGER = Logger.getLogger(PassCommandPage.class);
    private QuestionService questionService = new QuestionServiceImpl();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String testName = request.getParameter(AttributesResourceManager.getProperty("parameter.question"));
        List<Question> questionList =questionService.getAllQuestionsByTestName(testName);
        request.setAttribute(AttributesResourceManager.getProperty("parameter.list"),questionList);
        request.setAttribute(AttributesResourceManager.getProperty("parameter.test.name"),testName);
        LOGGER.info("Pass command execute");
        return PageResourceManager.getProperty("page.pass");
    }
}
