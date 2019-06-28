package ru.otus.hw06.atm;

import ru.otus.hw06.cash_bundle.ATMCashBox;
import ru.otus.hw06.operations.Operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ATM {

    public ATMCashBox atmCashBox;

    public void run() throws IOException {
        System.out.println("ATM - " + this);
        atmCashBox = ATMCashBox.set()
                .fiveThousand(9)
                .twoThousand(10)
                .oneThousand(20)
                .fiveHundred(1)
                .twoHundred(0)
                .oneHundred(100)
                .fifty(100)
                .build();
    }

//    public  void choiceOperation() throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Choose operation:\n" +
//                "1 - deposit\n" +
//                "2 - withdraw\n" +
//                "3 - balance\n" +
//                "4 - exit");
//        int choice = Integer.parseInt(reader.readLine());
//
//
//        choiceOperation(choice);
//    }

    public  void choiceOperation(Operations operation) throws IOException {

        switch (operation) {
            case DEPOSIT:
                Operations.DEPOSIT.execute(this);
                break;
            case WITHDRAW:
                Operations.WITHDRAW.execute(this);
                break;
            case BALANCE:
                Operations.BALANCE.execute(this);
                break;
        }
    }

}
