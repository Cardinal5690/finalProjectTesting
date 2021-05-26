package com.testing.controller.command.other.admin;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.PageResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminQuestionCreateCommandPage implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminQuestionCreateCommandPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Execute question create command");
        String subjectId = request.getParameter(AttributesResourceManager.getProperty("parameter.subject.id"));
        if(subjectId==null|| subjectId.isEmpty()){
            return PageResourceManager.getProperty("redirect.admin");
        }
        String subjectTitle= request.getParameter(AttributesResourceManager.getProperty("parameter.title"));
        String  testId = request.getParameter(AttributesResourceManager.getProperty("parameter.test.id"));
        request.setAttribute(AttributesResourceManager.getProperty("parameter.subject.id"),subjectId);
        request.setAttribute(AttributesResourceManager.getProperty("parameter.title"),subjectTitle);
        request.setAttribute(AttributesResourceManager.getProperty("parameter.test.id"), testId);
        return PageResourceManager.getProperty("page.admin.question.create");
    }
}
