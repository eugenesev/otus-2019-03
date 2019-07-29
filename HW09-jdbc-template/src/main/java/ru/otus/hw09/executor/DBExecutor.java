package ru.otus.hw09.executor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

public interface DBExecutor <T> {

    int create(String sql, Object object) throws SQLException, IllegalAccessException;

    <T> void update(String sql, long id, T param);

    Optional<T> load(String sql, long id, Function<ResultSet, T> rsHandler) throws SQLException;
}
