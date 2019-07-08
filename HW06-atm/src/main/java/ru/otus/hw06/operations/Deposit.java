package ru.otus.hw06.operations;

import ru.otus.hw06.atm.ATM;

import java.io.IOException;
import java.util.List;

public class Deposit implements Operation {

    private int consumerBalance;
    private int atmBalance;
    private int atmId;
    private List<Integer> consumerCashBundle;

    @Override
    public void execute(ATM atm) throws IOException {
        System.out.println("Depositing cash");
        atm.setAtmCashBox(atm.getAtmCashBox().debit(atm.getConsumerCashBundle()));
        atmId = atm.getId();
        consumerBalance = atm.getConsumerCashBundle().getBalance();
        consumerCashBundle = atm.getConsumerCashBundle().getCashBox();
        atmBalance = atm.getAtmCashBox().getBalance();

    }

    @Override
    public void printCheck() {
            System.out.println("ATM #" + atmId);
            System.out.println("Cash deposit " + consumerBalance + " was successful!");
            System.out.println(consumerCashBundle);
            System.out.println("ATM balance\n" + atmBalance);
    }
}
