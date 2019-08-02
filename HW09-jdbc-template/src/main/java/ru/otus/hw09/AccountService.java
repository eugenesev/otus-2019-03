package ru.otus.hw09;

import ru.otus.hw09.dao.Account;
import ru.otus.hw09.executor.DBExecutorImpl;

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

    public static void main(String[] args) throws SQLException, IllegalAccessException {
        Connection connection = DriverManager.getConnection(URL);
        connection.setAutoCommit(false);

        AccountService accountService = new AccountService(connection);

        accountService.createTableAccount();

        Account card = new Account(0, "Card", 123);
        accountService.saveAccount(card);
        accountService.getAccount(1);

    }

    public int createTableAccount() {
        try (PreparedStatement pst = connection.prepareStatement("create table account(no bigint(20) NOT NULL auto_increment, type varchar(255), rest number)")) {
            return pst.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
            return -1;
        }
    }

    public long saveAccount(Account account) throws IllegalAccessException {
        DBExecutorImpl dbExecutor = new DBExecutorImpl(connection);
        return dbExecutor.create("insert into account(type, rest) values (?, ?)", account);
    }

    public Optional<Account> getAccount(long no) {
        try {
            DBExecutorImpl<Account> executor = new DBExecutorImpl<>(connection);
            Optional<Account> account = executor.load("select no, type, rest from account where no  = ?", no, resultSet -> {
                try {
                    if (resultSet.next()) {
                        return new Account(resultSet.getLong("no"), resultSet.getString("type"), resultSet.getInt("rest"));
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
    public void updateAccount(long id, float rest) {
        DBExecutorImpl dbExecutor = new DBExecutorImpl(connection);
        dbExecutor.update("update account set rest = ? where id = ?", id, rest);
    }

    public void updateAccount(Account account) {
        DBExecutorImpl dbExecutor = new DBExecutorImpl(connection);
        dbExecutor.update("update account set rest = ? where id = ?", account);
    }

}
