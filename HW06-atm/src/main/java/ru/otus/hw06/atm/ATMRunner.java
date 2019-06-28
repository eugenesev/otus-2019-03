package ru.otus.hw06.atm;

import ru.otus.hw06.operations.Operations;

import java.io.IOException;

public class ATMRunner {

    public static void main(String[] args) throws IOException {
        ATM atm = new ATM();
        atm.run();

        atm.choiceOperation(Operations.DEPOSIT);
        atm.choiceOperation(Operations.WITHDRAW);

        ATM atm2 = new ATM();
        atm2.run();

        Operations.DEPOSIT.execute(atm2);
        Operations.WITHDRAW.execute(atm2);

    }

}
