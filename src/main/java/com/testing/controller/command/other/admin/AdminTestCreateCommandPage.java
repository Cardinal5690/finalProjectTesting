package com.testing.controller.command.other.admin;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.PageResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminTestCreateCommandPage implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminTestCreateCommandPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Execute create test page");
        String subjectId = request.getParameter(AttributesResourceManager.getProperty("parameter.subject.id"));
        if (subjectId == null || subjectId.isEmpty()) {
            return PageResourceManager.getProperty("redirect.admin");
        }
        String title = request.getParameter(AttributesResourceManager.getProperty("parameter.title"));
        request.setAttribute(AttributesResourceManager.getProperty("parameter.title"), title);
        request.setAttribute(AttributesResourceManager.getProperty("parameter.subject.id"), subjectId);
        return PageResourceManager.getProperty("page.admin.test.create");
    }
}
