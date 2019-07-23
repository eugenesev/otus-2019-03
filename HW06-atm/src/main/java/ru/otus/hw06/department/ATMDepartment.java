package ru.otus.hw06.department;

import ru.otus.hw06.atm.ATM;

public interface ATMDepartment {

    void init(ATM atm);

    void restoreATM();

    long getATMBalance();

}
