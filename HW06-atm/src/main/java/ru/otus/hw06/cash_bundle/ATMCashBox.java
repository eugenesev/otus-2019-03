package ru.otus.hw06.cash_bundle;

import java.util.ArrayList;
import java.util.List;

public class ATMCashBox {
    private int fiveThousand;
    private int twoThousand;
    private int oneThousand;
    private int fiveHundred;
    private int twoHundred;
    private int oneHundred;
    private int fifty;

    private List<Integer> cashBox = new ArrayList<>();
    private int balance;

    private ATMCashBox() {
    }

    public int getBalance() {
        return balance;
    }

    public static Builder set() {
        return new ATMCashBox().new Builder();
    }

    public ATMCashBox credit(int cashValue) {
        return this.new Credit().calculate(cashValue);
    }

    public ATMCashBox debit(ConsumerCashBundle consumerCashBundle) {

        return this.new Debit().calculate(consumerCashBundle);
    }

    @Override
    public String toString() {
        return "ATMCashBox{" +
                "fiveThousand=" + fiveThousand +
                ", twoThousand=" + twoThousand +
                ", oneThousand=" + oneThousand +
                ", fiveHundred=" + fiveHundred +
                ", twoHundred=" + twoHundred +
                ", oneHundred=" + oneHundred +
                ", fifty=" + fifty +
                '}';
    }

    public class Builder {

        private Builder() {
        }

        public Builder fiveThousand(int value) {
            ATMCashBox.this.fiveThousand = value;
            return this;
        }

        public Builder twoThousand(int value) {
            ATMCashBox.this.twoThousand = value;
            return this;
        }

        public Builder oneThousand(int value) {
            ATMCashBox.this.oneThousand = value;
            return this;
        }

        public Builder fiveHundred(int value) {
            ATMCashBox.this.fiveHundred = value;
            return this;
        }

        public Builder twoHundred(int value) {
            ATMCashBox.this.twoHundred = value;
            return this;
        }

        public Builder oneHundred(int value) {
            ATMCashBox.this.oneHundred = value;
            return this;
        }

        public Builder fifty(int value) {
            ATMCashBox.this.fifty = value;
            return this;
        }

        public ATMCashBox build() {
            cashBoxLoad();
            ATMCashBox.this.balance = fiveThousand * 5000
                    + twoThousand * 2000
                    + oneThousand * 1000
                    + fiveHundred * 500
                    + twoHundred * 200
                    + oneHundred * 100
                    + fifty * 50;
            return ATMCashBox.this;
        }

    }


    private class Debit {
        private Debit() {
        }

        private ATMCashBox calculate(ConsumerCashBundle consumerCashBundle) {
            for (int i = 0; i <= cashBox.size() - 1; i++) {
                cashBox.set(i, (cashBox.get(i) + consumerCashBundle.getCashBundle().get(i)));
            }
            cashBoxPush();


            ATMCashBox.this.balance = fiveThousand * 5000
                    + twoThousand * 2000
                    + oneThousand * 1000
                    + fiveHundred * 500
                    + twoHundred * 200
                    + oneHundred * 100
                    + fifty * 50;
            return ATMCashBox.this;
        }

    }

    private class Credit {

        private int cashValue;
        private ConsumerCashBundle consumerCashBundle;

        private Credit() {
        }

        private ATMCashBox calculate(int cashValue) {
            this.cashValue = cashValue;

            consumerCashBundle = ConsumerCashBundle.set()
                    .fiveThousand(notesCount(5000))
                    .twoThousand(notesCount(2000))
                    .oneThousand(notesCount(1000))
                    .fiveHundred(notesCount(500))
                    .twoHundred(notesCount(200))
                    .oneHundred(notesCount(100))
                    .fifty(notesCount(50))
                    .build();

            List<Integer> consumerCash = consumerCashBundle.getCashBundle();
            for (int i = 0; i <= cashBox.size() - 1; i++) {
                int notes = cashBox.get(i) - consumerCash.get(i);
                if (notes < 0) {
                    notes = -notes;
                    cashBox.set(i, (0));
                    consumerCash.set(i, consumerCash.get(i) - notes);
                    switch (i) {
                        case 0:
                        case 3:
                            consumerCash.set(i + 1, (consumerCash.get(i + 1) + notes * 5 / 2));
                            notes = (notes * 5) % 2;
                            consumerCash.set(i + 2, consumerCash.get(i + 2) + notes);
                            break;
                        case 1:
                        case 2:
                        case 4:
                        case 5:
                            consumerCash.set(i + 1, (consumerCash.get(i + 1) + notes * 2));
                            break;
                    }
                } else {
                    cashBox.set(i, (notes));
                }
            }

            cashBoxPush();
            consumerCashBundle.cashBundlePush();

            ATMCashBox.this.balance = fiveThousand * 5000
                    + twoThousand * 2000
                    + oneThousand * 1000
                    + fiveHundred * 500
                    + twoHundred * 200
                    + oneHundred * 100
                    + fifty * 50;

            printCheck();

            return ATMCashBox.this;
        }

        private int notesCount(int note) {
            int value = cashValue / note;
            cashValue -= note * value;
            return value;
        }

        private void printCheck(){
            System.out.println("Received money: \n" + consumerCashBundle);
        }

    }

    private void cashBoxLoad() {
        cashBox.add(fiveThousand);
        cashBox.add(twoThousand);
        cashBox.add(oneThousand);
        cashBox.add(fiveHundred);
        cashBox.add(twoHundred);
        cashBox.add(oneHundred);
        cashBox.add(fifty);
    }

    private void cashBoxPush() {
        fiveThousand = cashBox.get(0);
        twoThousand = cashBox.get(1);
        oneThousand = cashBox.get(2);
        fiveHundred = cashBox.get(3);
        twoHundred = cashBox.get(4);
        oneHundred = cashBox.get(5);
        fifty = cashBox.get(6);
    }

}
