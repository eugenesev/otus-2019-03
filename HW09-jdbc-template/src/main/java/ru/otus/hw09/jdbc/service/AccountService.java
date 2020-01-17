package ru.otus.hw09.jdbc.service;

import ru.otus.hw09.api.model.Account;
import ru.otus.hw09.jdbc.dao.DBExecutorImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class AccountService {

    private static final String URL = "jdbc:h2:mem:";
    private final Connection connection;

    public AccountService(Connection connection) {
        this.connection = connection;
    }

    public static void main(String[] args) throws SQLException, NoSuchFieldException, IllegalAccessException {
        try(Connection connection = DriverManager.getConnection(URL)) {
            connection.setAutoCommit(false);

            AccountService accountService = new AccountService(connection);

            accountService.createTableAccount();

            Account card = new Account(0, "Card", 123_000.3f);
            accountService.saveAccount(card);
            card = accountService.getAccount(1).get();
            card.setRest(100_000.3f);
            accountService.updateAccount(card);
            card = accountService.getAccount(1).get();
        }
    }

    public int createTableAccount() {
        try (PreparedStatement pst = connection.prepareStatement("create table account(no bigint(20) NOT NULL auto_increment, type varchar(255), rest number)")) {
            return pst.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
            return -1;
        }
    }

    public void saveAccount(Account account) throws SQLException {
        DBExecutorImpl dbExecutor = new DBExecutorImpl(connection);
        dbExecutor.create(account);
    }

    public Optional<Account> getAccount(long no) {
        try {
            DBExecutorImpl<Account> executor = new DBExecutorImpl<>(connection);
            Optional<Account> account = executor.load("select no, type, rest from account where no  = ?", no, resultSet -> {
                try {
                    if (resultSet.next()) {
                        return new Account(resultSet.getLong("no"), resultSet.getString("type"), resultSet.getFloat("rest"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });
            System.out.println("account:" + account);
            return account;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void updateAccount(Account account) throws NoSuchFieldException, IllegalAccessException, SQLException {
        DBExecutorImpl dbExecutor = new DBExecutorImpl(connection);
        dbExecutor.update(account);
    }

}
