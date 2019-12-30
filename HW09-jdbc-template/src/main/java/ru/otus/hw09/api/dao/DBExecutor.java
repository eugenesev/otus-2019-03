package ru.otus.hw09.api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

public interface DBExecutor<T> {

    void create(T object) throws SQLException, IllegalAccessException;

    void update(T object) throws NoSuchFieldException, IllegalAccessException, SQLException;

    Optional<T> load(String sql, long id, Function<ResultSet, T> rsHandler) throws SQLException;
}
