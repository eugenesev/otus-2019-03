package ru.otus.hw06.cash_bundle;

import java.util.ArrayList;
import java.util.List;

public class ConsumerCashBundle {
    private int fiveThousand;
    private int twoThousand;
    private int oneThousand;
    private int fiveHundred;
    private int twoHundred;
    private int oneHundred;
    private int fifty;

    private List<Integer> cashBundle = new ArrayList<>();
    private int cashValue;

    private ConsumerCashBundle() {
    }

    public int getCashValue(){return cashValue;}

    public List<Integer> getCashBundle(){return cashBundle;}

    public void cashBundlePush(){
        fiveThousand = cashBundle.get(0);
        twoThousand = cashBundle.get(1);
        oneThousand = cashBundle.get(2);
        fiveHundred = cashBundle.get(3);
        twoHundred = cashBundle.get(4);
        oneHundred = cashBundle.get(5);
        fifty = cashBundle.get(6);
    }

    public static Builder set() {
        return new ConsumerCashBundle().new Builder();
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

    public class Builder{

        private Builder() {
        }

        public Builder fiveThousand(int value) {
            ConsumerCashBundle.this.fiveThousand = value;
            return this;
        }

        public Builder twoThousand(int value) {
            ConsumerCashBundle.this.twoThousand = value;
            return this;
        }

        public Builder oneThousand(int value) {
            ConsumerCashBundle.this.oneThousand = value;
            return this;
        }

        public Builder fiveHundred(int value) {
            ConsumerCashBundle.this.fiveHundred = value;
            return this;
        }

        public Builder twoHundred(int value) {
            ConsumerCashBundle.this.twoHundred = value;
            return this;
        }

        public Builder oneHundred(int value) {
            ConsumerCashBundle.this.oneHundred = value;
            return this;
        }

        public Builder fifty(int value) {
            ConsumerCashBundle.this.fifty = value;
            return this;
        }

        public ConsumerCashBundle build() {
            cashBundleLoad();
            ConsumerCashBundle.this.cashValue = fiveThousand * 5000
                    + twoThousand * 2000
                    + oneThousand * 1000
                    + fiveHundred * 500
                    + twoHundred * 200
                    + oneHundred * 100
                    + fifty * 50;
            return ConsumerCashBundle.this;
        }
    }

    private void cashBundleLoad(){
        cashBundle.add(fiveThousand);
        cashBundle.add(twoThousand);
        cashBundle.add(oneThousand);
        cashBundle.add(fiveHundred);
        cashBundle.add(twoHundred);
        cashBundle.add(oneHundred);
        cashBundle.add(fifty);
    }
}
