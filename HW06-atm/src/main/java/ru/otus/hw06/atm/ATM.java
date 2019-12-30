package ru.otus.hw06.atm;

import ru.otus.hw06.money.ATMCashBox;
import ru.otus.hw06.money.ATMCashBoxCaretaker;
import ru.otus.hw06.money.ConsumerCashBundle;
import ru.otus.hw06.operations.Operation;

import java.io.IOException;
import java.sql.Connection;

public interface ATM extends ATMChain {

    ATMCashBox getAtmCashBox();

    void setAtmCashBox(ATMCashBox atmCashBox);

    ConsumerCashBundle getConsumerCashBundle();

    void putConsumerCashBundle(ConsumerCashBundle consumerCashBundle);

    void insertClientCard(long no);

    long getClientCardId();

    Connection getConnection();

    void setConnection(Connection connection);

    void choiceOperation(Operation operation) throws IOException;

    ATMCashBoxCaretaker getCaretaker();

}
