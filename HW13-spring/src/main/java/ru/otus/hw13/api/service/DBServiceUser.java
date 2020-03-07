package ru.otus.hw13.api.service;

import ru.otus.hw13.domain.User;

import java.util.List;
import java.util.Optional;

public interface DBServiceUser {

    long saveUser(User user);

    Optional<User> getUser(long id);

    List<User> getAllUsers();
}
