package ru.otus.hw06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw06.operations.ValueAsker;
import ru.otus.hw06.operations.WithdrawValueAsker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WithdrawValueAskerTest {

    @Test
    @DisplayName("Позитивный тест")
    public void asksForValueMultipleOf50(){
        ValueAsker asker = mock(ValueAsker.class);
        when(asker.ask("Enter value")).thenReturn(50);
        assertEquals(50, WithdrawValueAsker.getWithdrawValueFromUser(asker));
    }

}
