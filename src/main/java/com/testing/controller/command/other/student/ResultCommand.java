package com.testing.controller.command.other.student;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.CommandUtil;
import com.testing.dao.util.QueriesResourceManager;
import com.testing.model.entity.Question;
import com.testing.model.entity.Test;
import com.testing.model.entity.User;
import com.testing.model.service.QuestionService;
import com.testing.model.service.TestResultService;
import com.testing.model.service.TestService;
import com.testing.model.service.impl.QuestionServiceImpl;
import com.testing.model.service.impl.TestResultServiceImpl;
import com.testing.model.service.impl.TestServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ResultCommand.class);
    private TestResultService testResultService = new TestResultServiceImpl();
    private QuestionService questionService = new QuestionServiceImpl();
    private TestService testService = new TestServiceImpl();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributesResourceManager.getProperty("parameter.user"));
        String testName = request.getParameter(AttributesResourceManager.getProperty("parameter.test"));
        String[] answers =
                request.getParameterValues(AttributesResourceManager.getProperty("parameter.answer"));
        String[] questions =
                request.getParameterValues(AttributesResourceManager.getProperty("parameter.question"));

        Test test = testService.getByName(testName);
        List<Question> questionListFromDB = questionService.getAllQuestionsByTestName(testName);
        Map<String, String> userAnswers = new HashMap<>();
        for (int i = 0; i<questions.length;i++){
            userAnswers.put(questions[i], answers[i]);
        }
        testResultService.calculateResult(user.getId(), test,userAnswers,questionListFromDB);
        LOGGER.info("Calculate result execute");
        return CommandUtil.getUserPageByRole(user.getRole().getROLE());
    }
}
