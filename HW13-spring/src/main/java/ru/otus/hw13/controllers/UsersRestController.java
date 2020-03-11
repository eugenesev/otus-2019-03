package ru.otus.hw13.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw13.api.service.DBServiceUser;
import ru.otus.hw13.domain.PhoneDataSet;
import ru.otus.hw13.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class UsersRestController {

    private final DBServiceUser dbServiceUser;

    @Autowired
    public UsersRestController(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/users")
    protected String userUpdate(@ModelAttribute User user) {
        User newUser = new User();
        if (user.getName().equals("")) {
            newUser.setName("User dont have a name!");
        } else {
            newUser.setName(user.getName());
        }
        newUser.setAge(user.getAge());
        newUser.setHomeAddress(user.getHomeAddress());
        for (PhoneDataSet phone : user.getPhone()) {
            if (!phone.getNumber().equals("")) {
                newUser.addPhone(phone);
            }
        }
        dbServiceUser.saveUser(newUser);
        return "User Saved!";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/users")
    protected String userSave(@ModelAttribute User user, HttpServletRequest request) {

        User updatingUser = dbServiceUser.getUser(Integer.parseInt(request.getParameter("id"))).orElse(null);

        updatingUser.setName(user.getName());
        updatingUser.setAge(user.getAge());
        updatingUser.getHomeAddress().setStreet(user.getHomeAddress().getStreet());
        assertAndSetUserPhones(user, updatingUser);
        dbServiceUser.saveUser(updatingUser);
        return "User Saved!";
    }

    private void assertAndSetUserPhones(User userFromClient, User newUser) {
        List<PhoneDataSet> phonesFromClient = userFromClient.getPhone();
        List<PhoneDataSet> phonesFromDB = newUser.getPhone();
        outer:
        for (PhoneDataSet dbPhone : phonesFromDB) {
            for (PhoneDataSet clPhone : phonesFromClient) {
                if (!clPhone.getNumber().equals("")) {
                    dbPhone.setNumber(clPhone.getNumber());
                    clPhone.setNumber("");
                    continue outer;
                }
            }
        }
        for (PhoneDataSet clPhone : phonesFromClient) {
            if (!clPhone.getNumber().equals("")) {
                clPhone.setPerson(newUser);
                phonesFromDB.add(clPhone);
            }
        }
    }
}
