package ru.otus.hw06.department;

import ru.otus.hw06.atm.ATM;

import java.sql.SQLException;

public interface ATMDepartment {

    void init(ATM atm) throws SQLException;

    void restoreATM();

    long getATMBalance();

}
