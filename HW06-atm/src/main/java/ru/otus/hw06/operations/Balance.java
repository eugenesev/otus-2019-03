package ru.otus.hw06.operations;

import ru.otus.hw06.atm.ATM;

import java.io.IOException;

public class Balance implements Operation{
    @Override
    public void execute(ATM atm) throws IOException {
        System.out.println("ATM balance\n" + atm.atmCashBox.getBalance() + "\n" + atm.atmCashBox);
    }
}
