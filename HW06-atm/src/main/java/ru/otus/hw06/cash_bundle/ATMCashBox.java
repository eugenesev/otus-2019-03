package ru.otus.hw06.cash_bundle;

import java.util.List;

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

    public void restoreState(Memento memento) {
        getCashBox().clear();
        getCashBox().addAll(memento.getState());
        cashBoxPush();
        calculateBalance();

    }

    public class Builder extends CashBox.Builder<Builder> {

        @Override
        public ATMCashBox build() {
            cashBoxLoad();
            calculateBalance();
            return ATMCashBox.this;
        }
    }

    private class Debit {
        private Debit() {
        }

        private ATMCashBox calculate(ConsumerCashBundle consumerCashBundle) {
            for (int i = 0; i <= getCashBox().size() - 1; i++) {
                getCashBox().set(i, (getCashBox().get(i) + consumerCashBundle.getCashBox().get(i)));
            }
            cashBoxPush();
            calculateBalance();
            return ATMCashBox.this;
        }
    }

    private class Credit {
        private Credit() {
        }

        private ATMCashBox calculate(int cashValue) {

            ATMCashBoxCaretaker caretaker = new ATMCashBoxCaretaker();
            caretaker.setMemento(ATMCashBox.this.saveState());

            consumerCashBundle = ConsumerCashBundle.setBox(cashValue);

            List<Integer> consumerCash = consumerCashBundle.getCashBox();

                for (int i = 0; i <= getCashBox().size() - 1; i++) {
                    int notes = getCashBox().get(i) - consumerCash.get(i);
                    if (notes < 0) {
                        notes = -notes;
                        getCashBox().set(i, (0));
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
                            case 6:
                                if (consumerCash.get(i) > getCashBox().get(i)) {
                                    ATMCashBox.this.restoreState(caretaker.getMemento());
                                    for (int j = 0; j <= consumerCash.size() - 1; j++){
                                        consumerCash.set(j, 0);
                                    }
                                    System.out.println("Нет мелких купюр");
                                }
                                break;
                        }
                    } else {
                        getCashBox().set(i, (notes));
                    }
                }

            cashBoxPush();
            consumerCashBundle.cashBoxPush();
            consumerCashBundle.calculateBalance();
            calculateBalance();

            return ATMCashBox.this;
        }
    }
}
