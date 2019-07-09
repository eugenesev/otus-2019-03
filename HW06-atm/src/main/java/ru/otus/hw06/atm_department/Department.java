package ru.otus.hw06.atm_department;

import ru.otus.hw06.atm.ATM;
import ru.otus.hw06.cash_bundle.ATMCashBox;

public class Department {

    private static int overallBalance;

    public static void sumBalances (int balance) {
        overallBalance+=balance;
    }
    public static void print(){
        System.out.println("Overall balance: " + overallBalance);
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
                .fiveThousand(9)
                .twoThousand(10)
                .oneThousand(20)
                .fiveHundred(1)
                .twoHundred(0)
                .oneHundred(0)
                .fifty(0)
                .build();
        ATMCashBox atmCashBox_3 = ATMCashBox.set()
                .fiveThousand(9)
                .twoThousand(10)
                .oneThousand(20)
                .fiveHundred(1)
                .twoHundred(0)
                .oneHundred(0)
                .fifty(0)
                .build();


        ATM atm1 = new ATM(1, atmCashBox_1);
        ATM atm2 = new ATM(2, atmCashBox_2);
        ATM atm3 = new ATM(3, atmCashBox_3);

                atm1.linkWith(atm2).linkWith(atm3);
                atm1.sendATMBalance();
                Department.print();
                atm1.restore();



    }
}
