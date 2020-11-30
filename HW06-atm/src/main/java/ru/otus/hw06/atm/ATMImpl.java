package ru.otus.hw06.atm;

import ru.otus.hw06.money.ATMCashBox;
import ru.otus.hw06.money.ATMCashBoxCaretaker;
import ru.otus.hw06.money.ConsumerCashBundle;
import ru.otus.hw06.operations.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ATMImpl implements ATM {

    private ATM nextATM;
    private int counter;
    private int id;
    private ATMCashBox atmCashBox;
    private ConsumerCashBundle consumerCashBundle;
    private ATMCashBoxCaretaker caretaker;
    private long clientCardId;
    private Connection connection;

    public ATMImpl(int id, ATMCashBox atmCashBox) {
        this.id = id;
        this.atmCashBox = atmCashBox;
        caretaker = new ATMCashBoxCaretaker();
        caretaker.setMemento(this.atmCashBox.saveState());
    }

    @Override
    public ATMCashBox getAtmCashBox() {
        return atmCashBox;
    }

    @Override
    public void setAtmCashBox(ATMCashBox atmCashBox) {
        this.atmCashBox = atmCashBox;
    }

    @Override
    public ConsumerCashBundle getConsumerCashBundle() {
        return consumerCashBundle;
    }

    @Override
    public void putConsumerCashBundle(ConsumerCashBundle consumerCashBundle) {
        this.consumerCashBundle = consumerCashBundle;
    }

    @Override
    public void insertClientCard(long no) {
        this.clientCardId = no;
    }

    @Override
    public long getClientCardId() {
        return this.clientCardId;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ATMCashBoxCaretaker getCaretaker() {
        return caretaker;
    }

    @Override
    public int restore() {
        System.out.println(this + " restored");
        this.getAtmCashBox().restoreState(caretaker.getMemento());
        counter = restoreNext() + 1;
        return counter;
    }

    @Override
    public void linkWith(ATM nextATM) {
        this.nextATM = nextATM;
    }

    @Override
    public ATM getNextATM() {
        return nextATM;
    }

    private void checkNext() {
        if (nextATM != null) {
            nextATM.getATMCashBoxBalance();
        }
    }

    private int restoreNext() {
        if (nextATM != null) {
            return nextATM.restore();
        }
        return 0;
    }

    @Override
    public void choiceOperation(Operation operation) throws IOException, IllegalAccessException, NoSuchFieldException, SQLException {
        if (clientCardId != 0) {
            operation.execute(this);
            operation.printCheck();
        } else {
            System.out.println("Insert your card, please");
        }
    }

    @Override
    public int getATMCashBoxBalance() {
        int balance = this.getAtmCashBox().getBalance();
        System.out.println(this + " " + balance);
        return balance;

    }

    @Override
    public String toString() {
        return "ATM #" + id;
    }

}
