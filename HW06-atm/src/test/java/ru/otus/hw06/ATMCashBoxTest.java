package ru.otus.hw06;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw06.money.ATMCashBox;
import ru.otus.hw06.money.ConsumerCashBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ATMCashBoxTest {

    ATMCashBox atmCashBox;
    ConsumerCashBundle consumerCashBundle;

    @BeforeEach
    @DisplayName("atmCashBoxInitialisation")
    public void atmCashBoxInitialisation() {

        ATMCashBox atmCashBox = ATMCashBox.set()
                .fiveThousand(1)
                .twoThousand(1)
                .oneThousand(1)
                .fiveHundred(1)
                .twoHundred(1)
                .oneHundred(1)
                .fifty(1)
                .build();
        this.atmCashBox = atmCashBox;

        ConsumerCashBundle consumerCashBundle = ConsumerCashBundle.set()
                .fiveThousand(0)
                .twoThousand(0)
                .oneThousand(0)
                .fiveHundred(0)
                .twoHundred(0)
                .oneHundred(0)
                .fifty(1)
                .build();
        this.consumerCashBundle = consumerCashBundle;
    }

    @Test
    @DisplayName("Проверка баланса")
    public void balanceChecking() {
        assertEquals(8850, atmCashBox.getBalance());
    }

    @Test
    @DisplayName("Внесение средств")
    public void debitTest() {
        atmCashBox.debit(consumerCashBundle);
        assertEquals(8900, atmCashBox.getBalance());
    }

    @Test
    @DisplayName("Снятие средств")
    public void creditTest(){
        atmCashBox.credit(50);
        assertEquals(8800, atmCashBox.getBalance());
    }
}
