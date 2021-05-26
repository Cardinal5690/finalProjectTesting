package com.testing.controller.command.other.admin;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.model.service.TestService;
import com.testing.model.service.impl.TestServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminTestDeleteCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminTestDeleteCommand.class);
    private final TestService testService = new TestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Test delete command execute");
        String subjectId = request.getParameter(AttributesResourceManager.getProperty("parameter.subject.id"));
        String subjectTitle= request.getParameter(AttributesResourceManager.getProperty("parameter.title"));
        request.setAttribute(AttributesResourceManager.getProperty("parameter.subject.id"),subjectId);
        request.setAttribute(AttributesResourceManager.getProperty("parameter.title"),subjectTitle);

        String testId = request.getParameter(AttributesResourceManager.getProperty("parameter.test.id"));
        int testIdPareToInt = Integer.parseInt(testId);
        testService.delete(testIdPareToInt);
        return new AdminTestCommand().execute(request,response);
    }
}
