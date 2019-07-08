package ru.otus.hw06.atm_department;

import ru.otus.hw06.atm.ATM;
import ru.otus.hw06.cash_bundle.ATMCashBox;

public class Department {
    private static DepartmentContext departmentContext;

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

        departmentContext = new DepartmentContext();

        Chain chain = new ATM(1,atmCashBox_1)
                .linkWith(new ATM(2,atmCashBox_2))
                .linkWith(new ATM(3,atmCashBox_3));

        departmentContext.setChain(chain);



        boolean success;

        do {
            success = departmentContext.getATMBalance();
        } while (!success);

    }
}
