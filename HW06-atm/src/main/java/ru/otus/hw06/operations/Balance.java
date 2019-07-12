package ru.otus.hw06.operations;

import ru.otus.hw06.atm.ATM;

import java.io.IOException;
import java.util.List;

public class Balance implements Operation {
    private int atmBalance;
    private List<Integer> atmCashBox;
    private ATM atm;

    @Override
    public void execute(ATM atm) throws IOException {
        this.atm = atm;
        atmBalance = atm.getAtmCashBox().getBalance();
        atmCashBox = atm.getAtmCashBox().getCashBox();
    }

    @Override
    public void printCheck() {
            System.out.println(atm);
            System.out.println("ATM balance\n" + atmBalance + "\n" + atmCashBox);
    }
}
