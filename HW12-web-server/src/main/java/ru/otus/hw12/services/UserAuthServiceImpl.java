package ru.otus.hw12.services;


import ru.otus.hw10.api.service.DBServiceUser;

public class UserAuthServiceImpl implements UserAuthService {

    private final DBServiceUser dbServiceUser;

    public UserAuthServiceImpl(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return dbServiceUser.getUser(Integer.parseInt(login))
                .map(user -> user.getName().equals(password))
                .orElse(false);
    }

}
