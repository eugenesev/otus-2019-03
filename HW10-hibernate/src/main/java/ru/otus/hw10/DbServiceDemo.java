package ru.otus.hw10;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw10.api.dao.UserDao;
import ru.otus.hw10.api.model.HomeAddress;
import ru.otus.hw10.api.model.PhoneDataSet;
import ru.otus.hw10.api.model.User;
import ru.otus.hw10.api.service.DBServiceUser;
import ru.otus.hw10.hibernate.service.DbServiceUserImpl;
import ru.otus.hw10.hibernate.HibernateUtils;
import ru.otus.hw10.hibernate.dao.UserDaoHibernate;
import ru.otus.hw10.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.List;
import java.util.Optional;

public class DbServiceDemo {
    private static final Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtils
                .buildSessionFactory("hibernate.cfg.xml", User.class, HomeAddress.class, PhoneDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

        User user1 = new User("Вася", 25);
        HomeAddress address = new HomeAddress("Садовая");
        user1.setHomeAddress(address);

        PhoneDataSet phone1 = new PhoneDataSet("112-22-33");
        user1.addPhone(phone1);

        PhoneDataSet phone2 = new PhoneDataSet("112-44-55");
        user1.addPhone(phone2);

        long id = dbServiceUser.saveUser(user1);
        Optional<User> mayBeCreatedUser = dbServiceUser.getUser(id);

        user1.setHomeAddress(new HomeAddress("Лесная"));
        id = dbServiceUser.saveUser(user1);
        Optional<User> mayBeUpdatedUser = dbServiceUser.getUser(id);

        List<User> list = dbServiceUser.getAllUsers();
        System.out.println("users list:");
        list.forEach(user -> System.out.println("id=" + user.getId() + " name=" + user.getName()));

        System.out.println(user1);
        outputUserOptional("Created user", mayBeCreatedUser);
        outputUserOptional("Updated user", mayBeUpdatedUser);


    }

    private static void outputUserOptional(String header, Optional<User> mayBeUser) {
        System.out.println("-----------------------------------------------------------");
        System.out.println(header);
        mayBeUser.ifPresentOrElse(System.out::println, () -> logger.info("User not found"));
    }
}
