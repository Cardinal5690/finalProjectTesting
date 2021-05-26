package com.testing.controller.command.other.admin;

import com.testing.controller.command.Command;
import com.testing.controller.util.AttributesResourceManager;
import com.testing.controller.util.PageResourceManager;
import com.testing.model.service.TestResultService;
import com.testing.model.service.impl.TestResultServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AdminHistoryOfResultsCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminHistoryOfResultsCommand.class);
    private final TestResultService testResultService = new TestResultServiceImpl();
    public static final int RECORDS_FOR_PAGES = 5;
    public static final int FIRST_CURRENT_PAGE = 1;
    private int currentPage;
    private int numberOfPages;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Execute admin history");
        getCurrentPage(request);
        request.setAttribute(AttributesResourceManager.getProperty("parameter.results"), testResultService.findTestResultPagination(currentPage, RECORDS_FOR_PAGES));
        numberOfPages();
        request.setAttribute(AttributesResourceManager.getProperty("parameter.number.of.pages"), numberOfPages);
        request.setAttribute(AttributesResourceManager.getProperty("parameter.current.page"), currentPage);
        request.setAttribute(AttributesResourceManager.getProperty("parameter.records.per.page"), RECORDS_FOR_PAGES);
        return PageResourceManager.getProperty("page.admin.history");
    }

    private void getCurrentPage(HttpServletRequest request) {
        Optional<String> page = Optional.ofNullable(request.getParameter("currentPage"));
        currentPage = page.map(Integer::valueOf).orElse(FIRST_CURRENT_PAGE);
    }

    private void numberOfPages() {
        int rows = testResultService.getNumberOfRows();
        numberOfPages = rows / RECORDS_FOR_PAGES;
        if (numberOfPages % RECORDS_FOR_PAGES > 0) {
            numberOfPages++;
        }
    }
}
