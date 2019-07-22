package ru.otus.hw06.atm;

import ru.otus.hw06.money.ATMCashBox;
import ru.otus.hw06.money.ATMCashBoxCaretaker;
import ru.otus.hw06.money.ConsumerCashBundle;
import ru.otus.hw06.operations.*;

import java.io.IOException;

public class ATMImpl implements ATM {

    private ATM nextATM;
    private int counter;
    private int id;
    private ATMCashBox atmCashBox;
    private ConsumerCashBundle consumerCashBundle;
    private ATMCashBoxCaretaker caretaker;

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
    public ATMCashBoxCaretaker getCaretaker() {
        return caretaker;
    }

    @Override
    public void choiceOperation(Operation operation) throws IOException {
        operation.execute(this);
        operation.printCheck();
    }

    @Override
    public String toString() {
        return "ATM #" + id;
    }

    @Override
    public void linkWith(ATM nextATM) {
        this.nextATM = nextATM;
    }

    @Override
    public int getBalance() {
        int balance = this.getAtmCashBox().getBalance();
        System.out.println(this + " " + balance);
        return balance;

    }

    @Override
    public int restore() {
        System.out.println(this + " restored");
        this.getAtmCashBox().restoreState(caretaker.getMemento());
        counter = restoreNext() + 1;
        return counter;
    }

    @Override
    public ATM getNextATM() {
        return nextATM;
    }

    private void checkNext() {
        if (nextATM != null) {
            nextATM.getBalance();
        }
    }

    private int restoreNext() {
        if (nextATM != null) {
            return nextATM.restore();
        }
        return 0;
    }

}
