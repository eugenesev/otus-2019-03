package ru.otus.hw09;

import ru.otus.hw09.dao.User;
import ru.otus.hw09.executor.DBExecutorImpl;

import java.sql.*;
import java.util.Optional;

public class DBServiceImpl implements DBService {
    private static final String URL = "jdbc:h2:mem:";
    private final Connection connection;

    public DBServiceImpl(Connection connection) {
        this.connection = connection;
    }

    public static void main(String[] args) throws SQLException, IllegalAccessException {
        Connection connection = DriverManager.getConnection(URL);
        connection.setAutoCommit(false);

        DBServiceImpl dbServiceImpl = new DBServiceImpl(connection);
        dbServiceImpl.createTableUser();

        dbServiceImpl.saveUsers(new User(0, "John", 25));
        dbServiceImpl.saveUsers(new User(0, "Martin", 32));
        dbServiceImpl.saveUsers(new User(0, "David", 28));
        dbServiceImpl.updateName(2, "Ben");
        dbServiceImpl.updateAge(2, 54);

        dbServiceImpl.getUser(2);
    }

    public int createTableUser() {
        try (PreparedStatement pst = connection.prepareStatement("create table user(id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3))")) {
            return pst.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
            return -1;
        }
    }

    @Override
    public long saveUsers(User user) throws IllegalAccessException {
        DBExecutorImpl dbExecutor = new DBExecutorImpl(connection);
        return dbExecutor.create("insert into user(name, age) values (?, ?)", user);
    }

    public void updateName(long id, String name) {
        DBExecutorImpl dbExecutor = new DBExecutorImpl(connection);
        dbExecutor.update("update user set name = ? where id = ?", id, name);
    }

    public void updateAge(long id, int age) {
        DBExecutorImpl dbExecutor = new DBExecutorImpl(connection);
        dbExecutor.update("update user set age = ? where id = ?", id, age);
    }

    @Override
    public Optional<User> getUser(long id) {
        try {
            DBExecutorImpl<User> executor = new DBExecutorImpl<>(connection);
            Optional<User> user = executor.load("select id, name, age from user where id  = ?", id, resultSet -> {
                try {
                    if (resultSet.next()) {
                        return new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getInt("age"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });
            System.out.println("user:" + user);
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
