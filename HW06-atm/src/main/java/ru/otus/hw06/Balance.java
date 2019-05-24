package ru.otus.hw06;

public class Balance {

    private int balance;

    private Balance(){}

    private static Balance balanceObj = new Balance();

    public static Balance access (){
        return balanceObj;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
