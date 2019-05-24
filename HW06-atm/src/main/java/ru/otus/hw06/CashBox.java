package ru.otus.hw06;

public class CashBox {
    private int fiveThousand;
    private int twoThousand;
    private int oneThousand;
    private int fiveHundred;
    private int twoHundred;
    private int oneHundred;
    private int fifty;

    private CashBox(){}

    private static CashBox cashBoxObj = new CashBox();

    public static CashBox access (){
        return cashBoxObj;
    }


    public int getFiveThousand() {
        return fiveThousand;
    }

    public void setFiveThousand(int fiveThousand) {
        this.fiveThousand = fiveThousand;
    }

    public int getTwoThousand() {
        return twoThousand;
    }

    public void setTwoThousand(int twoThousand) {
        this.twoThousand = twoThousand;
    }

    public int getOneThousand() {
        return oneThousand;
    }

    public void setOneThousand(int oneThousand) {
        this.oneThousand = oneThousand;
    }

    public int getFiveHundred() {
        return fiveHundred;
    }

    public void setFiveHundred(int fiveHundred) {
        this.fiveHundred = fiveHundred;
    }

    public int getTwoHundred() {
        return twoHundred;
    }

    public void setTwoHundred(int twoHundred) {
        this.twoHundred = twoHundred;
    }

    public int getOneHundred() {
        return oneHundred;
    }

    public void setOneHundred(int oneHundred) {
        this.oneHundred = oneHundred;
    }

    public int getFifty() {
        return fifty;
    }

    public void setFifty(int fifty) {
        this.fifty = fifty;
    }
}
