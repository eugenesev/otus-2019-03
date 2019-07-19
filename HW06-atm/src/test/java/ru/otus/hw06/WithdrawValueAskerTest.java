package ru.otus.hw06;

import static ru.otus.hw06.operations.WithdrawValueAsker.getWithdrawValueFromUser;

import ru.otus.hw06.operations.WithdrawValueAsker;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WithdrawValueAskerTest {

    @Test
    public void getsWithdrawValue() throws Exception {
        WithdrawValueAsker asker = mock(WithdrawValueAsker.class);
        when(asker.ask(anyString())).thenReturn(1000);
        assertEquals(getWithdrawValueFromUser(asker), 1000);
    }

    @Test
    public void asksForWithdrawValueWhenLessThanFifty() throws Exception {
        WithdrawValueAsker asker = mock(WithdrawValueAsker.class);
        when(asker.ask("Enter value")).thenReturn(1);
        when(asker.ask("Wrong value! Value must be greater than 50, try again.")).thenReturn(50);
        getWithdrawValueFromUser(asker);
        verify(asker).ask("Wrong value! Value must be greater than 50, try again.");
    }
}
