package ru.otus.hw06.operations;

import ru.otus.hw06.atm.ATM;
import ru.otus.hw06.cash_bundle.ConsumerCashBundle;

import java.io.IOException;

public class Deposit implements Operation {

    @Override
    public void execute(ATM atm) throws IOException {
        System.out.println("Depositing cash");

        ConsumerCashBundle consumerCashBundle = ConsumerCashBundle.set()
                .fiveThousand(1)
                .twoThousand(0)
                .oneThousand(3)
                .oneHundred(3)
                .fifty(2)
                .build();
        atm.atmCashBox = atm.atmCashBox.debit(consumerCashBundle);

        System.out.println("Cash deposit " + consumerCashBundle.getCashValue()+ " was successful!");
        System.out.println(consumerCashBundle);
        System.out.println("ATM balance\n" + atm.atmCashBox.getBalance());
    }
}
