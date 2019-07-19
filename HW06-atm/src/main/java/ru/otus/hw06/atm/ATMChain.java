package ru.otus.hw06.atm;

public interface ATMChain {

    void linkWith(ATM nextATM);

    ATM getNextATM();

    int getBalance();

    int restore();

}
