package ru.otus.hw12.servlets;

import ru.otus.hw12.services.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AddUserServlet extends HttpServlet {

    private static final String EDIT_PAGE_TEMPLATE = "edit.html";

    private final TemplateProcessor templateProcessor;


    public AddUserServlet(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().println(templateProcessor.getPage(EDIT_PAGE_TEMPLATE, Collections.EMPTY_MAP));
    }


}
