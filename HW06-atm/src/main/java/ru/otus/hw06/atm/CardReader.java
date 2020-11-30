package ru.otus.hw06.atm;

import java.sql.Connection;

public interface CardReader {

    void insertClientCard(long no);

    long getClientCardId();

    Connection getConnection();

    void setConnection(Connection connection);

}
