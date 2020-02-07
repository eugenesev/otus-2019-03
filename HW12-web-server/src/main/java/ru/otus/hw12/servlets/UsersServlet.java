package ru.otus.hw12.servlets;

import ru.otus.hw10.api.service.DBServiceUser;
import ru.otus.hw12.services.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class UsersServlet extends HttpServlet {

    private static final String USERS_PAGE_TEMPLATE = "startpage.html";
    private static final String TEMPLATE_ATTR_RANDOM_USER = "User";
    private static final String TEMPLATE_ATTR_USER_PHONES = "Phones";

    private final DBServiceUser dbServiceUser;
    private final TemplateProcessor templateProcessor;

    public UsersServlet(TemplateProcessor templateProcessor, DBServiceUser dbServiceUser) {
        this.templateProcessor = templateProcessor;
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        dbServiceUser.getUser(1).ifPresent(user -> {paramsMap.put(TEMPLATE_ATTR_RANDOM_USER, user);
            paramsMap.put(TEMPLATE_ATTR_USER_PHONES, user.getPhone());});

        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, paramsMap));
    }

}
