package ru.otus.hw06.money;

public class ConsumerCashBundle extends CashBox {

    private static int cashValue;


    private ConsumerCashBundle() {
    }

    public static Builder set() {
        return new ConsumerCashBundle().new Builder();
    }

    public static ConsumerCashBundle setBox(int value) {
        cashValue = value;
        return ConsumerCashBundle.set()
                .fiveThousand(notesCount(Notes.FIVE_Thousand))
                .twoThousand(notesCount(Notes.TWO_Thousand))
                .oneThousand(notesCount(Notes.ONE_Thousand))
                .fiveHundred(notesCount(Notes.FIVE_Hundred))
                .twoHundred(notesCount(Notes.TWO_Hundred))
                .oneHundred(notesCount(Notes.ONE_Hundred))
                .fifty(notesCount(Notes.FIFTY))
                .build();

    }

    private static int notesCount(Notes note) {
        int value = cashValue / note.getNum();
        cashValue -= note.getNum() * value;
        return value;
    }


    public class Builder extends CashBox.Builder<Builder> {

        @Override
        public ConsumerCashBundle build() {
            calculateBalance();
            return ConsumerCashBundle.this;
        }
    }
}
