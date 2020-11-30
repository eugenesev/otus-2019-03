package ru.otus.hw06.department;

import ru.otus.hw06.atm.ATM;

public class Department implements ATMDepartment {

    private ATM atm;

    @Override
    public void init(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void restoreATM() {
        atm.restore();
    }

    @Override
    public long getATMBalance() {
        long overallBalance = 0;
        ATM currentATM = atm;
        while (currentATM != null) {
            overallBalance += currentATM.getATMCashBoxBalance();
            currentATM = currentATM.getNextATM();
        }
        System.out.println("Overall balance: " + overallBalance);
        return overallBalance;
    }
}
