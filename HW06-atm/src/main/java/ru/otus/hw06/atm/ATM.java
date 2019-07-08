package ru.otus.hw06.atm;

import ru.otus.hw06.atm_department.Chain;
import ru.otus.hw06.cash_bundle.ATMCashBox;
import ru.otus.hw06.cash_bundle.ATMCashBoxCaretaker;
import ru.otus.hw06.cash_bundle.ConsumerCashBundle;
import ru.otus.hw06.operations.*;

import java.io.IOException;

public class ATM extends Chain {
    private int id;

    private ATMCashBox atmCashBox;
    private ConsumerCashBundle consumerCashBundle;
    private Operation operation;
    private ATMCashBoxCaretaker caretaker;

    public ATM(int id, ATMCashBox atmCashBox) {
        this.id = id;
        this.atmCashBox = atmCashBox;
        caretaker = new ATMCashBoxCaretaker();
        caretaker.setMemento(this.atmCashBox.saveState());
    }

    public int getId() {
        return id;
    }

    public ATMCashBox getAtmCashBox() {
        return atmCashBox;
    }

    public void setAtmCashBox(ATMCashBox atmCashBox){
        this.atmCashBox = atmCashBox;
    }

    public ConsumerCashBundle getConsumerCashBundle() {
        return consumerCashBundle;
    }

    public void putConsumerCashBundle(ConsumerCashBundle consumerCashBundle) {
        this.consumerCashBundle = consumerCashBundle;
    }

    public void printCheck() {
        operation.printCheck();
    }

    public void choiceOperation(OperationEnum operationEnum) throws IOException {

        switch (operationEnum) {
            case DEPOSIT:
                operation = new Deposit();
                break;
            case WITHDRAW:
                operation = new Withdraw();
                break;
            case BALANCE:
                operation = new Balance();
                break;
            default:
        }
        operation.execute(this);
    }

    @Override
    public String toString() {
        return "ATM #" + id;
    }

    @Override
    public boolean getATMBalance() {
        int atmBalance = atmCashBox.getBalance();
        System.out.println(this + " " + atmBalance);
        return checkNext();
    }

    @Override
    public boolean restore() {
        System.out.println(this + " restored");
        atmCashBox.restoreState(caretaker.getMemento());
        return restoreNext();
    }
}
