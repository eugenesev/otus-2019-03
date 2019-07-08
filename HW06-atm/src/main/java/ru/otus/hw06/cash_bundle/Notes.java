package ru.otus.hw06.cash_bundle;

public enum Notes {
    FIVE_Thousand(5000),
    TWO_Thousand(2000),
    ONE_Thousand(1000),
    FIVE_Hundred(500),
    TWO_Hundred(200),
    ONE_Hundred(100),
    FIFTY(50);

    private int num;

    Notes(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

}
