package ru.otus.hw06;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.hw06.atm.ATM;
import ru.otus.hw06.atm.ATMImpl;
import ru.otus.hw06.department.Department;
import ru.otus.hw06.money.ATMCashBox;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartmentTest {

    Department department = new Department();

    @BeforeEach
    public void departmentInitialisation() {
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

        department.init(atm1);
    }

    @Test
    public void getATMBalance() {


        assertEquals(188700, department.getATMBalance());
    }

    @Test
    public void restoreATM() {


        department.restoreATM();
        assertEquals(4, department.getCounter());


//        ATM atm1 = Mockito.mock(ATMImpl.class);
//        ATM atm2 = Mockito.mock(ATMImpl.class);
//        ATM atm3 = Mockito.mock(ATMImpl.class);
//        ATM atm4 = Mockito.mock(ATMImpl.class);
//
//        atm1.linkWith(atm2);
//        atm2.linkWith(atm3);
//        atm3.linkWith(atm4);
//
//        Department department = new Department();
//        department.init(atm1);
//
//        department.restoreATM();
//
//        Mockito.verify(atm1, Mockito.times(1)).restore();
//        Mockito.verify(atm2, Mockito.times(1)).restore();

    }


}
