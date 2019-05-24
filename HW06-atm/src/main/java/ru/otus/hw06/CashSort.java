package ru.otus.hw06;

public class CashSort {



    public void sort(){
        System.out.println("Доступные номеналы");
        int balance = Balance.access().getBalance();
        CashBox.access().setFiveThousand(Math.round(balance/5000));
        System.out.println("5000 - " + CashBox.access().getFiveThousand());
        CashBox.access().setTwoThousand(Math.round((balance-CashBox.access().getFiveThousand()*5000)/2000));
        System.out.println("2000 - " + CashBox.access().getTwoThousand());
    }
}
