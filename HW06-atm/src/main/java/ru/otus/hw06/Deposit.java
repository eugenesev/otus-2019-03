package ru.otus.hw06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Deposit {



    public void deposit() throws IOException {
        System.out.println("Внесение наличных");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Внесите сумму");
        int value = Integer.parseInt(reader.readLine());
        int accountBalance = Balance.access().getBalance();
        Balance.access().setBalance(accountBalance+value);
        System.out.println("Вы внесли " + value + "руб. Ваш баланс: " + Balance.access().getBalance());
        new CashSort().sort();
        Choice.choiceOperation();
    }
}
