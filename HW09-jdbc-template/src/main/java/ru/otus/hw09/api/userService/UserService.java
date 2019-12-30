package ru.otus.hw09.api.userService;

import ru.otus.hw09.api.model.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserService {
    void saveUser(User user) throws SQLException, IllegalAccessException;
    void updateUser(User user) throws NoSuchFieldException, IllegalAccessException, SQLException;
    Optional<User> getUser(long id);
}
