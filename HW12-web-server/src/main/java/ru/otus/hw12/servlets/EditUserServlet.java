package ru.otus.hw12.servlets;

import ru.otus.hw10.api.model.HomeAddress;
import ru.otus.hw10.api.model.PhoneDataSet;
import ru.otus.hw10.api.model.User;
import ru.otus.hw10.api.service.DBServiceUser;
import ru.otus.hw12.services.TemplateProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class EditUserServlet extends HttpServlet {
    private static final int ID_PATH_PARAM_POSITION = 1;
    private static final String EDIT_PAGE_TEMPLATE = "edit.html";
    private static final String TEMPLATE_ATTR_USER = "User";
    private static final String TEMPLATE_ATTR_USER_PHONES = "Phones";

    private static final String PARAM_ID = "id";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_AGE = "age";
    private static final String PARAM_ADDRESS = "homeAddress";
    private static final String PARAM_PHONES = "phone";


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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long id = Long.parseLong(request.getParameter(PARAM_ID));
        User user = dbServiceUser.getUser(id).orElse(null);

        assertAndSetUserName(request, user);
        assertAndSetUserAge(request, user);
        assertAndSetUserHomeAddress(request, user);
        assertAndSetUserPhones(request, user);

        dbServiceUser.saveUser(user);
        response.sendRedirect("/");

    }

    private void assertAndSetUserPhones(HttpServletRequest request, User user) {
        List<String> phonesFromClient = new ArrayList<>();
        List<PhoneDataSet> phonesFromDB = user.getPhone();
        List<String> oldPhonesFromClient = new ArrayList<>();

        Optional.ofNullable(request.getParameterValues(PARAM_PHONES))
                .ifPresent(p -> Collections.addAll(phonesFromClient, p));
        for (String clPhone : phonesFromClient) {
            if (!clPhone.equals("")) {
                for (PhoneDataSet dbPhone : phonesFromDB) {
                    if (dbPhone.getNumber().equals(clPhone)) {
                        oldPhonesFromClient.add(clPhone);
                    }
                }
            }
        }
        phonesFromClient.removeAll(oldPhonesFromClient);
        for (String newClPhone : phonesFromClient) {
            if (!newClPhone.equals("")) {
                user.addPhone(new PhoneDataSet(newClPhone));
            }
        }
    }

    private void assertAndSetUserHomeAddress(HttpServletRequest request, User user) {
        if (request.getParameter(PARAM_ADDRESS).equals("")) {
            if (user.getHomeAddress() != null) {
                user.getHomeAddress().setStreet("");
            } else {
                user.setHomeAddress(new HomeAddress(""));
            }
        } else {
            if (user.getHomeAddress() != null) {
                user.getHomeAddress().setStreet(request.getParameter(PARAM_ADDRESS));
            } else {
                user.setHomeAddress(new HomeAddress(request.getParameter(PARAM_ADDRESS)));
            }
        }
    }

    private void assertAndSetUserAge(HttpServletRequest request, User user) {
        if (request.getParameter(PARAM_AGE).equals("")) {
            assert user != null;
            user.setAge(0);
        } else {
            assert user != null;
            user.setAge(Integer.parseInt(request.getParameter(PARAM_AGE)));
        }
    }

    private void assertAndSetUserName(HttpServletRequest request, User user) {
        if (request.getParameter(PARAM_NAME).equals("")) {
            throw new MyServletException("User dont have a name!");
        } else {
            if (user != null) {
                user.setName(request.getParameter(PARAM_NAME));
            }
        }
    }

}
