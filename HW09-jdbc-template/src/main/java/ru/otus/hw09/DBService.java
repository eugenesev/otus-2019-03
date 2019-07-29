package ru.otus.hw09;

import ru.otus.hw09.dao.User;

import java.sql.SQLException;
import java.util.Optional;

public interface DBService {
    long saveUsers(User user) throws SQLException, IllegalAccessException;
    Optional<User> getUser(long id);
}
