package ru.otus.hw06.atm;


import ru.otus.hw06.cash_bundle.ATMCashBox;
import ru.otus.hw06.cash_bundle.ConsumerCashBundle;
import ru.otus.hw06.operations.OperationEnum;

import java.io.IOException;

public class ATMRunner {

    public static void main(String[] args) throws IOException {

        ATMCashBox atmCashBox = ATMCashBox.set()
                .fiveThousand(9)
                .twoThousand(10)
                .oneThousand(20)
                .fiveHundred(1)
                .twoHundred(0)
                .oneHundred(0)
                .fifty(0)
                .build();
        ATM atm = new ATM(1,atmCashBox);

        ConsumerCashBundle consumerCashBundle = ConsumerCashBundle.set()
                .fiveThousand(1)
                .twoThousand(0)
                .oneThousand(3)
                .oneHundred(3)
                .fifty(2)
                .build();
        atm.putConsumerCashBundle(consumerCashBundle);

        atm.choiceOperation(OperationEnum.BALANCE);
        atm.printCheck();
        atm.choiceOperation(OperationEnum.DEPOSIT);
        atm.printCheck();
//        atm.choiceOperation(OperationEnum.WITHDRAW);
//        atm.printCheck();
        atm.choiceOperation(OperationEnum.BALANCE);
        atm.printCheck();
    }

}
