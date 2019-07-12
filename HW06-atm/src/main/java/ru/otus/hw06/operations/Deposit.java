package ru.otus.hw06.operations;

import ru.otus.hw06.atm.ATM;

import java.io.IOException;
import java.util.List;

public class Deposit implements Operation {

    private int consumerBalance;
    private int atmBalance;
    private ATM atm;
    private List<Integer> consumerCashBundle;

    @Override
    public void execute(ATM atm) throws IOException {
        System.out.println("Depositing cash");
        atm.setAtmCashBox(atm.getAtmCashBox().debit(atm.getConsumerCashBundle()));
        this.atm = atm;
        consumerBalance = atm.getConsumerCashBundle().getBalance();
        consumerCashBundle = atm.getConsumerCashBundle().getCashBox();
        atmBalance = atm.getAtmCashBox().getBalance();

    }

    @Override
    public void printCheck() {
            System.out.println(atm);
            System.out.println("Cash deposit " + consumerBalance + " was successful!");
            System.out.println(consumerCashBundle);
            System.out.println("ATMImpl balance\n" + atmBalance);
    }
}
