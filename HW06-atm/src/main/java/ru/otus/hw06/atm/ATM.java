package ru.otus.hw06.atm;

import ru.otus.hw06.money.ATMCashBox;
import ru.otus.hw06.money.ATMCashBoxCaretaker;
import ru.otus.hw06.money.ConsumerCashBundle;
import ru.otus.hw06.operations.Operation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface ATM extends ATMChain, CardReader {

    ATMCashBox getAtmCashBox();

    void setAtmCashBox(ATMCashBox atmCashBox);

    ATMCashBoxCaretaker getCaretaker();

    ConsumerCashBundle getConsumerCashBundle();

    void putConsumerCashBundle(ConsumerCashBundle consumerCashBundle);

    void choiceOperation(Operation operation) throws IOException, IllegalAccessException, NoSuchFieldException, SQLException;

}
