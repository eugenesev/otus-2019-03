package ru.otus.hw13.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw13.api.service.DBServiceUser;
import ru.otus.hw13.domain.HomeAddress;
import ru.otus.hw13.domain.PhoneDataSet;
import ru.otus.hw13.domain.User;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
public class UsersRestController {
    private static final Logger logger = LoggerFactory.getLogger(PhonesRestController.class);

    private final DBServiceUser dbServiceUser;

    @Autowired
    public UsersRestController(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/users")
    protected String userUpdate(@ModelAttribute User user) {
        User newUser = new User();
        if (user.getName().equals("")) {
            throw new MyServletException("User dont have a name!");
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
    protected String userSave(@ModelAttribute User user, HttpServletRequest request) throws IOException {

        User savingUser = dbServiceUser.getUser(Integer.parseInt(request.getParameter("id"))).orElse(null);

        savingUser.setName(user.getName());
        savingUser.setAge(user.getAge());
        savingUser.getHomeAddress().setStreet(user.getHomeAddress().getStreet());
        assertAndSetUserPhones(user, savingUser);
        dbServiceUser.saveUser(user);
        return "User Saved!";

    }
    private void assertAndSetUserPhones(User user, User newUser) {
        List<PhoneDataSet> phonesFromClient = user.getPhone();
        List<PhoneDataSet> phonesFromDB = newUser.getPhone();

        List<PhoneDataSet> newPhonesFromClient = findNewPhones(phonesFromClient, phonesFromDB);

        for (PhoneDataSet dbPhone : phonesFromDB) {
            if (!phonesFromClient.contains(dbPhone)) {
                if (!newPhonesFromClient.get(0).getNumber().equals("")) {
                    dbPhone.setNumber(newPhonesFromClient.get(0).getNumber());
                    newPhonesFromClient.remove(0);
                }
            }
        }
    }
    private List<PhoneDataSet> findNewPhones(List<PhoneDataSet> phonesFromClient, List<PhoneDataSet> phonesFromDB) {
        List<PhoneDataSet> oldPhones = new ArrayList<>();
        for (PhoneDataSet clPhone : phonesFromClient) {
            if (!clPhone.getNumber().equals("")) {
                for (PhoneDataSet dbPhone : phonesFromDB) {
                    if (dbPhone.getNumber().equals(clPhone.getNumber())) {
                        oldPhones.add(clPhone);
                    }
                }
            }
        }
        List<PhoneDataSet> newPhonesFromClient = new ArrayList<>(phonesFromClient);
        newPhonesFromClient.removeAll(oldPhones);
        return newPhonesFromClient;
    }
}
