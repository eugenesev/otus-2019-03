package ru.otus.hw06.operations;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WithdrawValueAsker {

    private final PrintStream out;
    private final InputStream in;

    public WithdrawValueAsker(InputStream in, PrintStream out) {
        this.out = out;
        this.in = in;
    }

    public int getWithdrawValueFromUser() {
        int input = ask("Enter value");
        while ((input % 50 > 0) || (input < 0))
            input = ask("Wrong value! Value must be  a multiple of 50, try again.");
        return input;
    }

    private int ask(String message) {
        Scanner scanner = new Scanner(in);
        out.println(message);
        int input;
        try{
            input = scanner.nextInt();
            return input;
        }catch (InputMismatchException ex){
            input = ask("Wrong value! Try again");
            return input;
        }

    }
}
