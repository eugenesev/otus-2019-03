package ru.otus.hw06.operations;

import ru.otus.hw06.atm.ATM;

import java.io.IOException;
import java.util.List;

public class Balance implements Operation {
    private int atmBalance;
    private List<Integer> atmCashBox;
    private int atmId;

    @Override
    public void execute(ATM atm) throws IOException {
        atmId = atm.getId();
        atmBalance = atm.getAtmCashBox().getBalance();
        atmCashBox = atm.getAtmCashBox().getCashBox();
    }

    @Override
    public void printCheck() {
            System.out.println("ATM #" + atmId);
            System.out.println("ATM balance\n" + atmBalance + "\n" + atmCashBox);
    }
}
