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
    protected String userUpdate(@ModelAttribute User user, @ModelAttribute ArrayList<PhoneDataSet> phoneDataSet, @ModelAttribute HomeAddress homeAddress) throws IOException {

        User newUser = new User();
        setUserName(user.getName(), newUser);
        setUserAge(user.getAge(), newUser);
        setUserHomeAddress(homeAddress, user);
        newUser.setPhone(phoneDataSet);
//        setUserPhones(request, user);

        dbServiceUser.saveUser(newUser);
        return "Готово!";

    }

//    @RequestMapping(method = RequestMethod.PUT, value = "/api/users")
//    protected String userSave() throws IOException {
//        long id = Long.parseLong(request.getParameter(PARAM_ID));
//        User user = dbServiceUser.getUser(id).orElse(null);
//
//        assertAndSetUserName(request, user);
//        assertAndSetUserAge(request, user);
//        assertAndSetUserHomeAddress(request, user);
//        assertAndSetUserPhones(request, user);
//
//        dbServiceUser.saveUser(user);
//        response.setContentType("text/html;charset=UTF-8");
//        ServletOutputStream out = response.getOutputStream();
//        out.print("Готово!");
//
//    }

//    private void assertAndSetUserAge(HttpServletRequest request, User user) {
//        if (request.getParameter(PARAM_AGE).equals("")) {
//            assert user != null;
//            user.setAge(0);
//        } else {
//            assert user != null;
//            user.setAge(Integer.parseInt(request.getParameter(PARAM_AGE)));
//        }
//    }
//
//    private void assertAndSetUserName(HttpServletRequest request, User user) {
//        if (request.getParameter(PARAM_NAME).equals("")) {
//            throw new MyServletException("User dont have a name!");
//        } else {
//            if (user != null) {
//                user.setName(request.getParameter(PARAM_NAME));
//            }
//        }
//    }
//
//    private void setUserPhones(HttpServletRequest request, User user) {
//        List<String> phones = new ArrayList<>();
//        Optional.ofNullable(request.getParameterValues(PARAM_PHONES))
//                .ifPresent(p -> Collections.addAll(phones, p));
//        for (String phone : phones) {
//            if (!phone.equals("")) {
//                user.addPhone(new PhoneDataSet(phone));
//            }
//        }
//    }

    private void setUserHomeAddress(HomeAddress address, User newUser) {
        if (!address.equals(null)) {
            newUser.setHomeAddress(address);
        }
    }

    private void setUserAge(Integer age, User newUser) {
        if (age == null) {
            newUser.setAge(0);
        } else {
            newUser.setAge(age);
        }
    }

    private void setUserName(String name, User newUser) {
        if (name.equals("")) {
            throw new MyServletException("User dont have a name!");
        } else {
            newUser.setName(name);
        }
    }

}
