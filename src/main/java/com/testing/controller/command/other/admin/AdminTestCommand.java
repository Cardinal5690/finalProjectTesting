package com.testing.controller.command.other.admin;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.PageResourceManager;
import com.testing.model.entity.Test;
import com.testing.model.service.TestService;
import com.testing.model.service.impl.TestServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminTestCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminTestCommand.class);
    private final TestService testService = new TestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Execute admin test command");
        String subjectId = request.getParameter(AttributesResourceManager.getProperty("parameter.subject.id"));
        request.setAttribute(AttributesResourceManager.getProperty("parameter.subject.id"), subjectId);
        String subjectTitle = request.getParameter(AttributesResourceManager.getProperty("parameter.title"));
        request.setAttribute(AttributesResourceManager.getProperty("parameter.title"), subjectTitle);
        if (subjectTitle == null || subjectTitle.isEmpty()) {
            return PageResourceManager.getProperty("redirect.admin");
        }
        List<Test> allTestBySubjectTitle = testService.getBySubjectTitle(subjectTitle);
        request.setAttribute(AttributesResourceManager.getProperty("parameter.list"), allTestBySubjectTitle );
        return PageResourceManager.getProperty("page.admin.test");
    }
}
