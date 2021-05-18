package com.testing.controller.command.other.student;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.PageResourceManager;
import com.testing.model.service.SubjectService;
import com.testing.model.service.impl.SubjectServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubjectPage implements Command {
    private static final Logger LOGGER = Logger.getLogger(SubjectPage.class);
    private SubjectService subjectService = new SubjectServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(AttributesResourceManager.getProperty("parameter.list"), subjectService.findAll());
        LOGGER.info("Execute");
        return PageResourceManager.getProperty("page.subject");
    }
}
