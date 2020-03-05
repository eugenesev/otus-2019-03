package ru.otus.hw12.db;

import ru.otus.hw10.api.model.HomeAddress;
import ru.otus.hw10.api.model.PhoneDataSet;
import ru.otus.hw10.api.model.User;
import ru.otus.hw10.api.service.DBServiceUser;

public class DbInitializerImpl implements DbInitializer {

    private final DBServiceUser dbServiceUser;

    public DbInitializerImpl(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    public void init() {

        User user1 = new User("Вася", 25);
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
