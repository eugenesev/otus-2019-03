package ru.otus.hw06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw06.operations.ValueAsker;
import ru.otus.hw06.operations.WithdrawValueAsker;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WithdrawValueAskerTest {

    @Test
    @DisplayName("asksForValueMultipleOf50")
    public void asksForValueMultipleOf50(){
        ValueAsker asker = mock(ValueAsker.class);
        when(asker.ask("Enter value")).thenReturn(50);
        assertEquals(50, WithdrawValueAsker.getWithdrawValueFromUser(asker));
    }

    @Test
    @DisplayName("asksForValueNotMultipleOf50")
    public void asksForValueNotMultipleOf50(){
        ValueAsker asker = mock(ValueAsker.class);
        when(asker.ask("Enter value")).thenReturn(10);
        when(asker.ask("Wrong value! Value must be  a multiple of 50, try again.")).thenReturn(50);

        WithdrawValueAsker.getWithdrawValueFromUser(asker);

        verify(asker).ask("Wrong value! Value must be  a multiple of 50, try again.");
    }

    @Test
    @DisplayName("asksForValueNotANumber")
    public void asksForValueNotANumber(){
        ValueAsker asker = mock(ValueAsker.class);
        when(asker.ask("Enter value")).thenThrow(InputMismatchException.class);
        when(asker.ask("Wrong value! Try again")).thenReturn(50);

        WithdrawValueAsker.getWithdrawValueFromUser(asker);

        verify(asker).ask("Wrong value! Try again");
    }


}
