package ru.otus.hw06.operations;

import ru.otus.hw06.atm.ATM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Withdraw implements Operation {

    @Override
    public void execute(ATM atm) throws IOException {
        System.out.println("Withdrawing cash");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter value");
        int cashValue = Integer.parseInt(reader.readLine());
        if (atm.atmCashBox.getBalance() - cashValue >= 0) {
            if (cashValue % 50 == 0) {
                atm.atmCashBox = atm.atmCashBox.credit(cashValue);
                System.out.println("Withdraw " + cashValue + " was successful.\n ATM balance: " + atm.atmCashBox.getBalance() + atm.atmCashBox);
            } else {
                System.out.println("Value must be a multiple of 50");
                atm.choiceOperation(Operations.WITHDRAW);
            }
        } else System.out.println("There is not enough money in the ATM");
//        atm.choiceOperation();
    }
}
