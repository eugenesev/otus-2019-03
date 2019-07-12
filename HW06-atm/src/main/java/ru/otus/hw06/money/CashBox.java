package ru.otus.hw06.money;

import java.util.ArrayList;
import java.util.List;

public class CashBox {

    private int fiveThousand;
    private int twoThousand;
    private int oneThousand;
    private int fiveHundred;
    private int twoHundred;
    private int oneHundred;
    private int fifty;

    private List<Integer> cashBox = new ArrayList<>();
    private int balance;

    protected CashBox() {
    }

    public static Builder set() {
        return new CashBox().new Builder();
    }

    public int getBalance() {
        return balance;
    }

    public List<Integer> getCashBox() {
        return cashBox;
    }

    public void setFiveThousand(int fiveThousand) {
        this.fiveThousand = fiveThousand;
    }

    public void setTwoThousand(int twoThousand) {
        this.twoThousand = twoThousand;
    }

    public void setOneThousand(int oneThousand) {
        this.oneThousand = oneThousand;
    }

    public void setFiveHundred(int fiveHundred) {
        this.fiveHundred = fiveHundred;
    }

    public void setTwoHundred(int twoHundred) {
        this.twoHundred = twoHundred;
    }

    public void setOneHundred(int oneHundred) {
        this.oneHundred = oneHundred;
    }

    public void setFifty(int fifty) {
        this.fifty = fifty;
    }

    public void cashBoxLoad() {
        cashBox.add(fiveThousand);
        cashBox.add(twoThousand);
        cashBox.add(oneThousand);
        cashBox.add(fiveHundred);
        cashBox.add(twoHundred);
        cashBox.add(oneHundred);
        cashBox.add(fifty);
    }

    public void cashBoxPush() {
        fiveThousand = cashBox.get(0);
        twoThousand = cashBox.get(1);
        oneThousand = cashBox.get(2);
        fiveHundred = cashBox.get(3);
        twoHundred = cashBox.get(4);
        oneHundred = cashBox.get(5);
        fifty = cashBox.get(6);
    }

    public void calculateBalance() {
        balance = fiveThousand * Notes.FIVE_Thousand.getNum()
                + twoThousand * Notes.TWO_Thousand.getNum()
                + oneThousand * Notes.ONE_Thousand.getNum()
                + fiveHundred * Notes.FIVE_Hundred.getNum()
                + twoHundred * Notes.TWO_Hundred.getNum()
                + oneHundred * Notes.ONE_Hundred.getNum()
                + fifty * Notes.FIFTY.getNum();
    }

    @Override
    public String toString() {
        return "fiveThousand=" + fiveThousand +
                ", twoThousand=" + twoThousand +
                ", oneThousand=" + oneThousand +
                ", fiveHundred=" + fiveHundred +
                ", twoHundred=" + twoHundred +
                ", oneHundred=" + oneHundred +
                ", fifty=" + fifty;
    }

    protected class Builder<B extends Builder<?>> {

        protected Builder() {
        }

        public B fiveThousand(int value) {
            CashBox.this.setFiveThousand(value);
            return (B) this;
        }

        public B twoThousand(int value) {
            CashBox.this.setTwoThousand(value);
            return (B) this;
        }

        public B oneThousand(int value) {
            CashBox.this.setOneThousand(value);
            return (B) this;
        }

        public B fiveHundred(int value) {
            CashBox.this.setFiveHundred(value);
            return (B) this;
        }

        public B twoHundred(int value) {
            CashBox.this.setTwoHundred(value);
            return (B) this;
        }

        public B oneHundred(int value) {
            CashBox.this.setOneHundred(value);
            return (B) this;
        }

        public B fifty(int value) {
            CashBox.this.setFifty(value);
            return (B) this;
        }

        public CashBox build() {
            cashBoxLoad();
            calculateBalance();
            return CashBox.this;
        }
    }

}
