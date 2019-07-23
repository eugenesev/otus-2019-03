package ru.otus.hw06.department;

import ru.otus.hw06.atm.ATM;
import ru.otus.hw06.atm.ATMImpl;
import ru.otus.hw06.money.ATMCashBox;

public class Department implements ATMDepartment {

    private ATM atm;

    int counter;

    public int getCounter() {
        return counter;
    }


    public ATM getAtm() {
        return atm;
    }

    public static void main(String[] args) {

        ATMCashBox atmCashBox_1 = ATMCashBox.set()
                .fiveThousand(9)
                .twoThousand(10)
                .oneThousand(20)
                .fiveHundred(1)
                .twoHundred(0)
                .oneHundred(0)
                .fifty(0)
                .build();
        ATMCashBox atmCashBox_2 = ATMCashBox.set()
                .fiveThousand(1)
                .twoThousand(1)
                .oneThousand(1)
                .fiveHundred(1)
                .twoHundred(1)
                .oneHundred(1)
                .fifty(1)
                .build();


        ATM atm1 = new ATMImpl(1, atmCashBox_1);
        ATM atm2 = new ATMImpl(2, atmCashBox_2);
        ATM atm3 = new ATMImpl(3, atmCashBox_1);
        ATM atm4 = new ATMImpl(4, atmCashBox_2);


        atm1.linkWith(atm2);
        atm2.linkWith(atm3);
        atm3.linkWith(atm4);

        Department department = new Department();
        department.init(atm1);
        department.getATMBalance();
        department.restoreATM();

    }


    @Override
    public void init(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void restoreATM() {

        counter = atm.restore();
    }

    @Override
    public long getATMBalance() {
        long overallBalance = 0;
        ATM currentATM = atm;
        while (currentATM != null) {
            overallBalance += currentATM.getBalance();
            currentATM = currentATM.getNextATM();
        }
        System.out.println("Overall balance: " + overallBalance);
        return overallBalance;
    }
}
