package ru.otus.hw06.atm;

import ru.otus.hw06.atm_department.Department;
import ru.otus.hw06.cash_bundle.ATMCashBox;
import ru.otus.hw06.cash_bundle.ATMCashBoxCaretaker;
import ru.otus.hw06.cash_bundle.ConsumerCashBundle;
import ru.otus.hw06.operations.*;

import java.io.IOException;

public class ATMImpl implements ATM {

    private ATM nextATM;
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
    public void sendATMBalance(Department department) {
        int atmBalance = this.getAtmCashBox().getBalance();
        System.out.println(this + " " + atmBalance);
        department.sumBalances(atmBalance);
        checkNext(department);
    }

    @Override
    public void restore() {
        System.out.println(this + " restored");
        atmCashBox.restoreState(caretaker.getMemento());
        restoreNext();
    }

    private void checkNext(Department department) {
        if (nextATM != null) {
            nextATM.sendATMBalance(department);
        }
    }

    private void restoreNext() {
        if (nextATM != null) {
            nextATM.restore();
        }
    }

}
