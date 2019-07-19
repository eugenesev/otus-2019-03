package ru.otus.hw06.money;

import java.util.Map;

public class ATMCashBox extends CashBox {

    private ConsumerCashBundle consumerCashBundle;

    private ATMCashBox() {
    }

    public ConsumerCashBundle getConsumerCashBundle() {
        return consumerCashBundle;
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

    public Memento saveState() {
        return new Memento(getCashBox());
    }

    public boolean restoreState(Memento memento) {
        getCashBox().clear();
        getCashBox().putAll(memento.getState());
        calculateBalance();
        return true;
    }

    public class Builder extends CashBox.Builder<Builder> {

        @Override
        public ATMCashBox build() {
            calculateBalance();
            return ATMCashBox.this;
        }
    }

    private class Debit {
        private Debit() {
        }

        private ATMCashBox calculate(ConsumerCashBundle consumerCashBundle) {

            for (Map.Entry<Notes, Integer> ee : getCashBox().entrySet()) {
                Notes key = ee.getKey();
                ee.setValue(ee.getValue() + consumerCashBundle.getCashBox().get(key));
            }
            calculateBalance();
            return ATMCashBox.this;
        }
    }

    private class Credit {

        private Map<Notes, Integer> consumerCash;
        private ATMCashBoxCaretaker caretaker;

        private Credit() {
        }

        private ATMCashBox calculate(int cashValue) {

            caretaker = new ATMCashBoxCaretaker();
            caretaker.setMemento(ATMCashBox.this.saveState());

            consumerCashBundle = ConsumerCashBundle.setBox(cashValue);

            consumerCash = consumerCashBundle.getCashBox();

            calculateNote(Notes.FIVE_Thousand);
            calculateNote(Notes.TWO_Thousand);
            calculateNote(Notes.ONE_Thousand);
            calculateNote(Notes.FIVE_Hundred);
            calculateNote(Notes.TWO_Hundred);
            calculateNote(Notes.ONE_Hundred);
            calculateNote(Notes.FIFTY);

            if (consumerCash != null) {
                consumerCashBundle.calculateBalance();
                calculateBalance();
            }
            return ATMCashBox.this;
        }

        private void calculateNote(Notes note) {
            int notes = getCashBox().get(note) - consumerCash.get(note);

            if (notes < 0) {
                notes = -notes;
                getCashBox().put(note, (0));

                noteCase(note, notes);

            } else {
                getCashBox().put(note, notes);
            }
        }

        private void noteCase(Notes note, int notes) {

            switch (note) {
                case FIVE_Thousand:
                    consumerCash.put(Notes.TWO_Thousand, (consumerCash.get(Notes.TWO_Thousand) + notes * 5 / 2));
                    notes = (notes * 5) % 2;
                    consumerCash.put(Notes.ONE_Thousand, consumerCash.get(Notes.ONE_Thousand) + notes);
                    break;
                case TWO_Thousand:
                    consumerCash.put(Notes.ONE_Thousand, (consumerCash.get(Notes.ONE_Thousand) + notes * 2));
                    break;
                case ONE_Thousand:
                    consumerCash.put(Notes.FIVE_Hundred, (consumerCash.get(Notes.FIVE_Hundred) + notes * 2));
                    break;
                case FIVE_Hundred:
                    consumerCash.put(Notes.TWO_Hundred, (consumerCash.get(Notes.TWO_Hundred) + notes * 5 / 2));
                    notes = (notes * 5) % 2;
                    consumerCash.put(Notes.ONE_Hundred, consumerCash.get(Notes.ONE_Hundred) + notes);
                    break;
                case TWO_Hundred:
                    consumerCash.put(Notes.ONE_Hundred, (consumerCash.get(Notes.ONE_Hundred) + notes * 2));
                    break;
                case ONE_Hundred:
                    consumerCash.put(Notes.FIFTY, (consumerCash.get(Notes.FIFTY) + notes * 2));
                    break;
                case FIFTY:
                    if (consumerCash.get(note) > getCashBox().get(note)) {
                        ATMCashBox.this.restoreState(caretaker.getMemento());
                        consumerCash.clear();
                        System.out.println("There is no small change in ATM!");
                    }
                    break;
            }
        }
    }
}
