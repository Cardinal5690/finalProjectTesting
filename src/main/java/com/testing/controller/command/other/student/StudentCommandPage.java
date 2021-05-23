package com.testing.controller.command.other.student;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.PageResourceManager;
import com.testing.model.entity.Test;
import com.testing.model.entity.TestResult;
import com.testing.model.entity.User;
import com.testing.model.service.TestResultService;
import com.testing.model.service.TestService;
import com.testing.model.service.impl.TestResultServiceImpl;
import com.testing.model.service.impl.TestServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentCommandPage implements Command {
    private static final Logger LOGGER = Logger.getLogger(StudentCommandPage.class);
    private TestResultService testResultService = new TestResultServiceImpl();
    private TestService testService = new TestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Student command page execute");
        User user = (User) request.getSession().getAttribute(AttributesResourceManager.getProperty("parameter.user"));
        List<TestResult> allUserResult = testResultService.allTestResultByUserId(user.getId());
        List<Test> allTest = testService.findAll();
        Map<String,Integer> test = new HashMap<>();
        for (TestResult testResult : allUserResult){
            String testName = allTest.stream().filter( testId -> testId.getId().equals(testResult.getTestId())).map(Test::getTestName).findAny().orElseThrow(RuntimeException::new);
            test.put(testName,testResult.getResult());
        }
        request.setAttribute(AttributesResourceManager.getProperty("parameter.map"),test);
        request.setAttribute(AttributesResourceManager.getProperty("parameter.name"), user.getName());
        request.setAttribute(AttributesResourceManager.getProperty("parameter.surname"), user.getSurname());
        request.setAttribute(AttributesResourceManager.getProperty("parameter.email"), user.getEmail());
        return PageResourceManager.getProperty("page.student");
    }
}
