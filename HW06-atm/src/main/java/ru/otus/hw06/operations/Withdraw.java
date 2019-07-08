package ru.otus.hw06.operations;

import ru.otus.hw06.atm.ATM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Withdraw implements Operation {

    private int consumerBalance;
    private List<Integer> consumerCashBundle;
    private int atmId;

    @Override
    public void execute(ATM atm) throws IOException {
        System.out.println("Withdrawing cash");
        int atmBalance = atm.getAtmCashBox().getBalance();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter value");
        int cashValue = Integer.parseInt(reader.readLine());
        if (atmBalance - cashValue >= 0) {
            if (cashValue % 50 == 0) {
                atm.setAtmCashBox(atm.getAtmCashBox().credit(cashValue));
                atm.putConsumerCashBundle(atm.getAtmCashBox().getConsumerCashBundle());
            } else {
                System.out.println("Value must be a multiple of 50");
                atm.choiceOperation(OperationEnum.WITHDRAW);
            }
        } else {
            System.out.println("There is not enough money in the ATM");
        }
        atmId = atm.getId();
        consumerCashBundle = atm.getConsumerCashBundle().getCashBox();
        consumerBalance = atm.getConsumerCashBundle().getBalance();
    }

    @Override
    public void printCheck() {
        System.out.println("ATM #" + atmId);
        System.out.println("Received money: \n" + consumerCashBundle);
        System.out.println("Withdraw " + consumerBalance + " RUB");
    }
}