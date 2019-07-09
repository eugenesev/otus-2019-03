package ru.otus.hw06.atm;

public abstract class ATMChain {
    private ATMChain next;

    public ATMChain linkWith(ATMChain next) {
        this.next = next;
        return this.next;
    }

    public abstract void sendATMBalance();

    public abstract void restore();

    protected void restoreNext () {
        if (next != null) {
            next.restore();
        }
    }

    protected void checkNext() {
        if (next != null) {
            next.sendATMBalance();
        }
    }

}
