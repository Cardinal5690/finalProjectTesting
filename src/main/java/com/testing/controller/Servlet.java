package com.testing.controller;

import com.testing.controller.command.Command;
import com.testing.controller.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        path = cleanPath(path);
        Command command = CommandFactory.getCommand(path);
        String page = command.execute(request, response);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replaceAll("redirect:","/testing"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    private String cleanPath(String path) {
        return  path.replaceAll(".*/testing/", "");
    }
}
