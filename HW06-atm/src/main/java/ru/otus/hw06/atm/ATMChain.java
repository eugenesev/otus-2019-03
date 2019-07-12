package ru.otus.hw06.atm;

import ru.otus.hw06.atm_department.Department;

public interface ATMChain {

    void linkWith(ATM nextATM);

    void sendATMBalance(Department department);

    void restore();

}
