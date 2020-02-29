package ru.otus.hw12.servlets;

import com.google.gson.Gson;
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

public class PhoneApiServlet extends HttpServlet {
    private static final int ID_PATH_PARAM_POSITION = 1;
    private final DBServiceUser dbServiceUser;
    private final Gson gson;


    public PhoneApiServlet(DBServiceUser dbServiceUser, Gson gson) {
        this.dbServiceUser = dbServiceUser;
        this.gson = gson;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = dbServiceUser.getUser(extractIdFromRequest(req)).orElse(null);
        List<String> phones = new ArrayList<>();

        if (user != null && !user.getPhone().isEmpty()) {
            user.getPhone().forEach(phone -> phones.add(phone.getNumber()));
            resp.setContentType("application/json;charset=UTF-8");
            ServletOutputStream out = resp.getOutputStream();
            out.print(gson.toJson(phones));
        } else {
            resp.setContentType("application/json;charset=UTF-8");
            ServletOutputStream out = resp.getOutputStream();
            out.print(gson.toJson(Collections.singleton("No Number")));
        }
    }

    private long extractIdFromRequest(HttpServletRequest request) {
        String[] path = request.getPathInfo().split("/");
        String id = (path.length > 1) ? path[ID_PATH_PARAM_POSITION] : String.valueOf(-1);
        return Long.parseLong(id);
    }
}
