package ru.otus.hw06.operations;

import ru.otus.hw06.atm.ATM;

import java.io.IOException;

public enum Operations implements Operation{
    DEPOSIT{
        @Override
        public void execute(ATM atm) throws IOException {
            new Deposit().execute(atm);
        }
    },
    WITHDRAW{
        @Override
        public void execute(ATM atm) throws IOException {
            new Withdraw().execute(atm);
        }
    },
    BALANCE {
        @Override
        public void execute(ATM atm) throws IOException {
            new Balance().execute(atm);
        }
    }
}
