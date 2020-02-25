package ru.otus.hw12.servlets;

import ru.otus.hw10.api.model.HomeAddress;
import ru.otus.hw10.api.model.PhoneDataSet;
import ru.otus.hw10.api.model.User;
import ru.otus.hw10.api.service.DBServiceUser;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AddUserApiServlet extends HttpServlet {

    private static final String PARAM_NAME = "name";
    private static final String PARAM_AGE = "age";
    private static final String PARAM_ADDRESS = "homeAddress";
    private static final String PARAM_PHONES = "phone";


    private final DBServiceUser dbServiceUser;

    public AddUserApiServlet(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = new User();

        assertAndSetUserName(request, user);
        assertAndSetUserAge(request, user);
        assertAndSetUserHomeAddress(request, user);
        assertAndSetUserPhones(request, user);

        dbServiceUser.saveUser(user);
        response.setContentType("text/html;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        out.print("Готово!");

    }

    private void assertAndSetUserPhones(HttpServletRequest request, User user) {
        List<String> phones = new ArrayList<>();
        Optional.ofNullable(request.getParameterValues(PARAM_PHONES))
                .ifPresent(p -> Collections.addAll(phones, p));
        for (String phone : phones) {
            if (!phone.equals("")) {
                user.addPhone(new PhoneDataSet(phone));
            }
        }
    }

    private void assertAndSetUserHomeAddress(HttpServletRequest request, User user) {
        if (!request.getParameter(PARAM_ADDRESS).equals("")) {
            user.setHomeAddress(new HomeAddress(request.getParameter(PARAM_ADDRESS)));
        }
    }

    private void assertAndSetUserAge(HttpServletRequest request, User user) {
        if (request.getParameter(PARAM_AGE).equals("")) {
            user.setAge(0);
        } else {
            user.setAge(Integer.parseInt(request.getParameter(PARAM_AGE)));
        }
    }

    private void assertAndSetUserName(HttpServletRequest request, User user) {
        if (request.getParameter(PARAM_NAME).equals("")) {
            throw new MyServletException("User dont have a name!");
        } else {
            user.setName(request.getParameter(PARAM_NAME));
        }
    }

}
