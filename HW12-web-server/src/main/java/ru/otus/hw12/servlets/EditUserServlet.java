package ru.otus.hw12.servlets;

import ru.otus.hw10.api.model.User;
import ru.otus.hw10.api.service.DBServiceUser;
import ru.otus.hw12.services.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class EditUserServlet extends HttpServlet {
    private static final int ID_PATH_PARAM_POSITION = 1;
    private static final String EDIT_PAGE_TEMPLATE = "edit.html";
    private static final String TEMPLATE_ATTR_USER = "user";
    private static final String TEMPLATE_ATTR_USER_PHONES = "phones";


    private final DBServiceUser dbServiceUser;
    private final TemplateProcessor templateProcessor;


    public EditUserServlet(TemplateProcessor templateProcessor, DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
        this.templateProcessor = templateProcessor;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        List<String> phones = new ArrayList<>();

        User user = dbServiceUser.getUser(extractIdFromRequest(req)).orElse(null);
        if (user != null) {
            user.getPhone().forEach(phone -> phones.add(phone.getNumber()));
        }

        paramsMap.put(TEMPLATE_ATTR_USER, user);
        paramsMap.put(TEMPLATE_ATTR_USER_PHONES, phones);
        resp.setContentType("text/html");
        resp.getWriter().println(templateProcessor.getPage(EDIT_PAGE_TEMPLATE, paramsMap));
    }

    private long extractIdFromRequest(HttpServletRequest request) {
        String[] path = request.getPathInfo().split("/");
        String id = (path.length > 1) ? path[ID_PATH_PARAM_POSITION] : String.valueOf(-1);
        return Long.parseLong(id);
    }
}
