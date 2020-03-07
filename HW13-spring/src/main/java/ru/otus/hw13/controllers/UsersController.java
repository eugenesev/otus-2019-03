package ru.otus.hw13.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.hw13.api.service.DBServiceUser;
import ru.otus.hw13.domain.User;

import java.util.List;

@Controller
public class UsersController {

    private final DBServiceUser dbServiceUser;

    @Autowired
    public UsersController(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @GetMapping({"/"})
    public String usersView(Model model) {
        List<User> users = dbServiceUser.getAllUsers();
        model.addAttribute("users", users);
        return "users.html";
    }

}
