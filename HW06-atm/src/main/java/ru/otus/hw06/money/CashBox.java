package ru.otus.hw06.money;

import java.util.HashMap;
import java.util.Map;

public class CashBox {

    private Map<Notes, Integer> cash = new HashMap<>();
    private int balance;

    protected CashBox() {
    }

    public int getBalance() {
        return balance;
    }

    public Map<Notes, Integer> getCashBox() {
        return cash;
    }

    public void setFiveThousand(int fiveThousand) {

        cash.put(Notes.FIVE_Thousand, fiveThousand);
    }

    public void setTwoThousand(int twoThousand) {

        cash.put(Notes.TWO_Thousand, twoThousand);
    }

    public void setOneThousand(int oneThousand) {

        cash.put(Notes.ONE_Thousand, oneThousand);
    }

    public void setFiveHundred(int fiveHundred) {

        cash.put(Notes.FIVE_Hundred, fiveHundred);
    }

    public void setTwoHundred(int twoHundred) {

        cash.put(Notes.TWO_Hundred, twoHundred);
    }

    public void setOneHundred(int oneHundred) {

        cash.put(Notes.ONE_Hundred, oneHundred);
    }

    public void setFifty(int fifty) {
        cash.put(Notes.FIFTY, fifty);
    }

    public void calculateBalance() {
        balance = 0;
        cash.forEach((k, v) -> balance += k.getNum() * v);
    }

    @Override
    public String toString() {
        return "fiveThousand=" + cash.get(Notes.FIVE_Thousand) +
                ", twoThousand=" + cash.get(Notes.TWO_Thousand) +
                ", oneThousand=" + cash.get(Notes.ONE_Thousand) +
                ", fiveHundred=" + cash.get(Notes.FIVE_Hundred) +
                ", twoHundred=" + cash.get(Notes.TWO_Hundred) +
                ", oneHundred=" + cash.get(Notes.ONE_Hundred) +
                ", fifty=" + cash.get(Notes.FIFTY);
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
            calculateBalance();
            return CashBox.this;
        }
    }

}
