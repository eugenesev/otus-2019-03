package ru.otus.hw06;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.hw06.atm.ATM;
import ru.otus.hw06.cash_bundle.ATMCashBox;
import ru.otus.hw06.cash_bundle.ConsumerCashBundle;
import ru.otus.hw06.operations.OperationEnum;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ATMTest {
    private ATM atm;

    @BeforeEach
    public void beforeATMTest() throws IOException {
        System.out.println("ATMCashBox.set()");
        ATMCashBox atmCashBox = ATMCashBox.set()
                .fiveThousand(9)
                .twoThousand(10)
                .oneThousand(20)
                .fiveHundred(1)
                .twoHundred(0)
                .oneHundred(0)
                .fifty(1)
                .build();

        ATM atm = new ATM(1, atmCashBox);
        this.atm = atm;
    }

    @AfterEach
    public void afterATMTest() throws IOException {
        System.out.println("Get balance");
        atm.choiceOperation(OperationEnum.BALANCE);
    }

    @Test
    public void deposit() throws InterruptedException, IOException {
        ConsumerCashBundle consumerCashBundle = ConsumerCashBundle.set()
                .fiveThousand(1)
                .twoThousand(0)
                .oneThousand(3)
                .oneHundred(3)
                .fifty(2)
                .build();
        atm.putConsumerCashBundle(consumerCashBundle);
        atm.choiceOperation(OperationEnum.DEPOSIT);
        assertEquals(93950, atm.getAtmCashBox().getBalance());
    }

    @Test
    public void withdraw() throws InterruptedException, IOException {
        atm.choiceOperation(OperationEnum.WITHDRAW);
        assertEquals(93950, atm.getAtmCashBox().getBalance());
    }

}
