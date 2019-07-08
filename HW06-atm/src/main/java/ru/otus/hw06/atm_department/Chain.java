package ru.otus.hw06.atm_department;

import ru.otus.hw06.atm.ATM;
import ru.otus.hw06.cash_bundle.ATMCashBox;

public abstract class Chain {
    private Chain next;

    public Chain linkWith(Chain next) {
        this.next = next;
        return next;
    }

    public abstract boolean getATMBalance();

    public abstract boolean restore();

    protected boolean restoreNext () {
        if (next == null) {
            return true;
        }
        return next.restore();
    }

    protected boolean checkNext() {
        if (next == null) {
            return true;
        }
        return next.getATMBalance();
    }

}
