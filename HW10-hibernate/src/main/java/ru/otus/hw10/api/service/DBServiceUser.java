package ru.otus.hw10.api.service;

import org.hibernate.ScrollableResults;
import ru.otus.hw10.api.model.User;

import java.util.List;
import java.util.Optional;

public interface DBServiceUser {

    long saveUser(User user);

    Optional<User> getUser(long id);

    List<User> getAllUsers();
}
