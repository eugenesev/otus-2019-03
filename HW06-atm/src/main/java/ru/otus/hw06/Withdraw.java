package ru.otus.hw06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Withdraw {



    int accountBalance;

    public void withdraw() throws IOException {
        System.out.println("Снятие наличных");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите сумму");
        int value = Integer.parseInt(reader.readLine());
        int accountBalance = Balance.access().getBalance();
        if (accountBalance-value>=0){
            Balance.access().setBalance(accountBalance-value);
            System.out.println("Вы сняли " + value + "руб. Ваш баланс: " + Balance.access().getBalance());
        }
        else System.out.println("Недостаточно средств на счете");
        Choice.choiceOperation();

    }
}
