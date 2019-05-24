package ru.otus.hw06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Choice {

    public static void choiceOperation() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose operation:\n" +
                "1 - deposit\n" +
                "2 - withdraw\n" +
                "3 - balance\n" +
                "4 - exit");
        int choice = Integer.parseInt(reader.readLine());

        switch (choice) {
            case 1:
                new Deposit().deposit();
                break;
            case 2:
                new Withdraw().withdraw();
                break;
            case 3:
                System.out.println("Ваш баланс\n" + Balance.access().getBalance());
                choiceOperation();
                break;
            case 4:
                System.out.println("До скорого :)");
        }
    }
}
