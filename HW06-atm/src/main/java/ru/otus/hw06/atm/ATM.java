package ru.otus.hw06.atm;

import ru.otus.hw06.cash_bundle.ATMCashBox;
import ru.otus.hw06.cash_bundle.ATMCashBoxCaretaker;
import ru.otus.hw06.cash_bundle.ConsumerCashBundle;
import ru.otus.hw06.operations.Operation;

import java.io.IOException;

public interface ATM extends ATMChain{

    ATMCashBox getAtmCashBox();

    void setAtmCashBox(ATMCashBox atmCashBox);

    ConsumerCashBundle getConsumerCashBundle();

    void putConsumerCashBundle(ConsumerCashBundle consumerCashBundle);

    void choiceOperation(Operation operation) throws IOException;

    ATMCashBoxCaretaker getCaretaker();

}
