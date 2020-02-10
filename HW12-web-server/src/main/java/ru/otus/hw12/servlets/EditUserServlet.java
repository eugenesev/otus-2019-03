package ru.otus.hw12.servlets;

import ru.otus.hw10.api.model.HomeAddress;
import ru.otus.hw10.api.model.User;
import ru.otus.hw10.api.service.DBServiceUser;
import ru.otus.hw12.services.TemplateProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditUserServlet extends HttpServlet {
    private static final int ID_PATH_PARAM_POSITION = 1;
    private static final String EDIT_PAGE_TEMPLATE = "edit.html";
    private static final String TEMPLATE_ATTR_USER = "User";
    private static final String TEMPLATE_ATTR_USER_PHONES = "Phones";

    private static final String PARAM_ID = "id";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_AGE = "age";
    private static final String PARAM_ADDRESS = "homeAddress";


    private final DBServiceUser dbServiceUser;
    private final TemplateProcessor templateProcessor;


    public EditUserServlet(TemplateProcessor templateProcessor, DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
        this.templateProcessor = templateProcessor;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        User user = dbServiceUser.getUser(extractIdFromRequest(req)).orElse(null);
        paramsMap.put(TEMPLATE_ATTR_USER, user);
        paramsMap.put(TEMPLATE_ATTR_USER_PHONES, user.getPhone().toString());

        resp.setContentType("text/html");
        resp.getWriter().println(templateProcessor.getPage(EDIT_PAGE_TEMPLATE, paramsMap));
    }

    private long extractIdFromRequest(HttpServletRequest request) {
        String[] path = request.getPathInfo().split("/");
        String id = (path.length > 1) ? path[ID_PATH_PARAM_POSITION] : String.valueOf(-1);
        return Long.parseLong(id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long id = Long.parseLong(request.getParameter(PARAM_ID));
        String name = request.getParameter(PARAM_NAME);
        int age = Integer.parseInt(request.getParameter(PARAM_AGE));
        String homeAddress = request.getParameter(PARAM_ADDRESS);

        User user = dbServiceUser.getUser(id).get();
        user.setName(name);
        user.setAge(age);
        user.setHomeAddress(new HomeAddress(homeAddress));
        dbServiceUser.saveUser(user);
        response.sendRedirect("/");

    }

}
