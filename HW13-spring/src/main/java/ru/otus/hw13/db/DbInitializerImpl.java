package ru.otus.hw13.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.hw13.api.service.DBServiceUser;
import ru.otus.hw13.domain.HomeAddress;
import ru.otus.hw13.domain.PhoneDataSet;
import ru.otus.hw13.domain.User;

import javax.annotation.PostConstruct;

@Component
public class DbInitializerImpl implements DbInitializer {

    private final DBServiceUser dbServiceUser;

    @Autowired
    public DbInitializerImpl(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }


    @Override
    @PostConstruct
    public void init() {

        User user1 = new User("Вася", 30);
        HomeAddress address = new HomeAddress("Садовая");
        user1.setHomeAddress(address);
        PhoneDataSet phone1 = new PhoneDataSet("112-22-33");
        user1.addPhone(phone1);
        PhoneDataSet phone2 = new PhoneDataSet("112-44-55");
        user1.addPhone(phone2);

        dbServiceUser.saveUser(user1);

        User user2 = new User("Дима", 30);
        HomeAddress address2 = new HomeAddress("Садовая");
        user2.setHomeAddress(address2);
        PhoneDataSet phone3 = new PhoneDataSet("255-22-55");
        user2.addPhone(phone3);

        dbServiceUser.saveUser(user2);
    }

}
