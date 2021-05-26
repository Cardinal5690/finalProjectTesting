package com.testing.controller.command.other.admin;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.PageResourceManager;
import com.testing.model.entity.Subject;
import com.testing.model.service.SubjectService;
import com.testing.model.service.impl.SubjectServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminSubjectCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminSubjectCommand.class);
    private final SubjectService subjectService = new SubjectServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Admin get subject page command");
        String locale = request.getParameter(AttributesResourceManager.getProperty("parameter.locale"));
        if (locale == null || locale.isEmpty()) {
            return PageResourceManager.getProperty("redirect.admin");
        }
        List<Subject> allSubjectByLocale =subjectService.findAllByLocale(locale);
        request.setAttribute(AttributesResourceManager.getProperty("parameter.list"), allSubjectByLocale);
        return PageResourceManager.getProperty("page.admin.subject");
    }
}
