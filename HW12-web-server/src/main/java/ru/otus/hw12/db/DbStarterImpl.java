package ru.otus.hw12.db;

import org.hibernate.SessionFactory;
import ru.otus.hw10.api.dao.UserDao;
import ru.otus.hw10.api.model.HomeAddress;
import ru.otus.hw10.api.model.PhoneDataSet;
import ru.otus.hw10.api.model.User;
import ru.otus.hw10.api.service.DBServiceUser;
import ru.otus.hw10.hibernate.HibernateUtils;
import ru.otus.hw10.hibernate.dao.UserDaoHibernate;
import ru.otus.hw10.hibernate.service.DbServiceUserImpl;
import ru.otus.hw10.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.Optional;

public class DbStarterImpl implements DbStarter {

    DBServiceUser dbServiceUser;

    @Override
    public void start() {
        SessionFactory sessionFactory = HibernateUtils
                .buildSessionFactory("hibernate.cfg.xml", User.class, HomeAddress.class, PhoneDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        dbServiceUser = new DbServiceUserImpl(userDao);

        User user1 = new User("Вася", 25);
        HomeAddress address = new HomeAddress("Садовая");
        user1.setHomeAddress(address);

        PhoneDataSet phone1 = new PhoneDataSet("112-22-33");
        user1.addPhone(phone1);

        PhoneDataSet phone2 = new PhoneDataSet("112-44-55");
        user1.addPhone(phone2);

        long id = dbServiceUser.saveUser(user1);
        Optional<User> mayBeCreatedUser = dbServiceUser.getUser(id);

        User user2 = new User("Дима", 30);
        HomeAddress address2 = new HomeAddress("Садовая");
        user2.setHomeAddress(address);

        long id2 = dbServiceUser.saveUser(user1);
    }

    @Override
    public DBServiceUser getDBServiceUser(){
        return dbServiceUser;
    }
}
