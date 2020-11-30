package ru.otus.hw06.operations;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ValueAsker {

    private final PrintStream out;
    private final InputStream in;

    public ValueAsker(InputStream in, PrintStream out) {
        this.out = out;
        this.in = in;
    }

    public int ask(String message) {
        Scanner scanner = new Scanner(in);
        out.println(message);
        return scanner.nextInt();
    }
}
