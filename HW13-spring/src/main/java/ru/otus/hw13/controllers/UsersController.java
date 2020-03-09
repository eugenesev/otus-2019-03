package ru.otus.hw13.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.hw13.api.service.DBServiceUser;
import ru.otus.hw13.domain.User;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {
    private static final Logger logger = LoggerFactory.getLogger(PhonesRestController.class);
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

    @GetMapping({"/users/{id}"})
    public String getEditUserPage(@PathVariable(value = "id") long id, Model model){
        User user = dbServiceUser.getUser(id).orElse(null);
        model.addAttribute("user", user);
        return "edit.html";
    }

    @GetMapping({"/users/new_user"})
    public String getAddUserPage(Model model){
        model.addAttribute("user", null);
        return "edit.html";
    }

}
