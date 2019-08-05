package ru.otus.hw09.userService;

import ru.otus.hw09.dao.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserService {
    long saveUsers(User user);
    void updateUser(User user);
    Optional<User> getUser(long id);
}
