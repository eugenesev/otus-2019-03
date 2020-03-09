package ru.otus.hw13.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw13.api.service.DBServiceUser;
import ru.otus.hw13.domain.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class PhonesRestController {
    private static final Logger logger = LoggerFactory.getLogger(PhonesRestController.class);
    private final DBServiceUser dbServiceUser;

    public PhonesRestController(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @RequestMapping(method = RequestMethod.GET, value = "api/phones/{id}")
    public List<String> getPhones(@PathVariable(value = "id") long id){
        User user = dbServiceUser.getUser(id).orElse(null);
        List<String> phones = new ArrayList<>();

        if (user != null && !user.getPhone().isEmpty()) {
            user.getPhone().forEach(phone -> phones.add(phone.getNumber()));
            return phones;
        } else {
           return (List<String>) Collections.singleton("No Number");
        }
    }

}
