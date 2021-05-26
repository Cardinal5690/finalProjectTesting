package com.testing.controller.command.other.student;

import com.testing.controller.command.Command;
import com.testing.controller.command.other.registration.RegistrationCommandPage;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.PageResourceManager;
import com.testing.model.entity.Test;
import com.testing.model.service.TestService;
import com.testing.model.service.impl.TestServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TestCommandPage implements Command {
    private static final Logger LOGGER = Logger.getLogger(TestCommandPage.class);
    private TestService testService = new TestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter(AttributesResourceManager.getProperty("parameter.title"));
        if (title == null || title.isEmpty()) {
            return PageResourceManager.getProperty("redirect.student");
        }
        List<Test> subjectTests = testService.getBySubjectTitle(title);
        request.setAttribute(AttributesResourceManager.getProperty("parameter.list"), subjectTests);
        LOGGER.info("Execute");
        return PageResourceManager.getProperty("page.test");
    }
}
