package ru.otus.hw06.operations;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class WithdrawValueAsker {

    private final Scanner scanner;
    private final PrintStream out;

    public WithdrawValueAsker(InputStream in, PrintStream out) {
        scanner = new Scanner(in);
        this.out = out;
    }

    public static int getWithdrawValueFromUser(WithdrawValueAsker asker) {
        int input = asker.ask("Enter value");
        while (input < 50)
            input = asker.ask("Wrong value! Value must be greater than 50, try again.");
        return input;
    }

    public int ask(String message) {
        out.println(message);
        return scanner.nextInt();
    }
}
