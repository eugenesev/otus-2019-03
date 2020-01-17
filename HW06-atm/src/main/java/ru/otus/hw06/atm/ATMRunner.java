package ru.otus.hw06.atm;


import ru.otus.hw06.money.ATMCashBox;
import ru.otus.hw06.money.ConsumerCashBundle;
import ru.otus.hw06.operations.Balance;
import ru.otus.hw06.operations.Deposit;
import ru.otus.hw06.operations.Withdraw;

import java.io.IOException;
import java.sql.SQLException;

public class ATMRunner {

    public static void main(String[] args) throws IOException, IllegalAccessException, NoSuchFieldException, SQLException {

        ATMCashBox atmCashBox = ATMCashBox.set()
                .fiveThousand(9)
                .twoThousand(10)
                .oneThousand(20)
                .fiveHundred(1)
                .twoHundred(0)
                .oneHundred(0)
                .fifty(0)
                .build();
        ATMImpl atmImpl = new ATMImpl(1, atmCashBox);

        ConsumerCashBundle consumerCashBundle = ConsumerCashBundle.set()
                .fiveThousand(1)
                .twoThousand(0)
                .oneThousand(3)
                .fiveHundred(0)
                .twoHundred(0)
                .oneHundred(3)
                .fifty(2)
                .build();
        atmImpl.putConsumerCashBundle(consumerCashBundle);

        atmImpl.choiceOperation(new Balance());
//        atmImpl.choiceOperation(new Deposit());
        atmImpl.choiceOperation(new Withdraw());
//        atmImpl.choiceOperation(new Balance());
    }

}
