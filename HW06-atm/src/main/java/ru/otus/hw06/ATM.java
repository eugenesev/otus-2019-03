package ru.otus.hw06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ATM {

    private int accountBalance;

    public void run() throws IOException {
        Choice.choiceOperation();
    }

    public void deposit(int value){
        accountBalance+=value;
        System.out.println("Вы внесли " + value + "руб.");

    }

    public void withdraw(int value){
        accountBalance-=value;
        System.out.println("Вы сняли "+value+"руб.");
    }

    public int getBalance(){
        System.out.println("Ваш баланс " + accountBalance);
        return accountBalance;
    }

}
