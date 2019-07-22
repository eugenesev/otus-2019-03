package ru.otus.hw06;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.hw06.atm.ATMImpl;
import ru.otus.hw06.money.ATMCashBox;
import ru.otus.hw06.money.ConsumerCashBundle;
import ru.otus.hw06.operations.Balance;
import ru.otus.hw06.operations.Deposit;
import ru.otus.hw06.operations.Withdraw;
import ru.otus.hw06.operations.WithdrawValueAsker;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ATMImplTest {
    private ATMImpl atmImpl;

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

        ATMImpl atmImpl = new ATMImpl(1, atmCashBox);
        this.atmImpl = atmImpl;
    }

    @AfterEach
    public void afterATMTest() throws IOException {
        System.out.println("Get balance");
        atmImpl.choiceOperation(new Balance());
    }

    @Test
    public void deposit() throws InterruptedException, IOException {
        ConsumerCashBundle consumerCashBundle = ConsumerCashBundle.set()
                .fiveThousand(1)
                .twoThousand(0)
                .oneThousand(3)
                .fiveHundred(0)
                .twoHundred(0)
                .oneHundred(3)
                .fifty(2)
                .build();
        atmImpl.putConsumerCashBundle(consumerCashBundle);
        atmImpl.choiceOperation(new Deposit());
        assertEquals(93950, atmImpl.getAtmCashBox().getBalance());
    }

    @Test
    public void withdraw() throws InterruptedException, IOException {

        Withdraw withdraw = mock(Withdraw.class);
        WithdrawValueAsker asker = mock(WithdrawValueAsker.class);

        when(asker.ask(anyString())).thenReturn(500);

        atmImpl.choiceOperation(withdraw);

        assertEquals(85550, atmImpl.getAtmCashBox().getBalance());
    }

}
