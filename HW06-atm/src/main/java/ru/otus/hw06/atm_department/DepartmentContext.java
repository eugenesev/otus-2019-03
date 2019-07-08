package ru.otus.hw06.atm_department;

import ru.otus.hw06.atm.ATM;
import ru.otus.hw06.cash_bundle.ATMCashBox;

public class DepartmentContext {

    private Chain chain;

    public void setChain(Chain chain) {
        this.chain = chain;
    }

    public boolean getATMBalance () {
        if (chain.getATMBalance()) {


            return true;
        }
        return false;
    }


    public boolean restoreATM () {
        if (chain.restore()) {
            System.out.println("ATM restored!");

            return true;
        }
        return false;
    }

}
