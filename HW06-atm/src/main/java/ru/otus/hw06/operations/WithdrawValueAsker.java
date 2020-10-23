package ru.otus.hw06.operations;

import java.util.InputMismatchException;

public class WithdrawValueAsker {

    public WithdrawValueAsker() {
    }

    public static int getWithdrawValueFromUser(ValueAsker asker) {
        int input;
        try {
            input = asker.ask("Enter value");
        }catch (InputMismatchException ex){
            input = asker.ask("Wrong value! Try again");
        }
        while ((input % 50 > 0) || (input < 0))
            input = asker.ask("Wrong value! Value must be  a multiple of 50, try again.");
        return input;
    }
}
