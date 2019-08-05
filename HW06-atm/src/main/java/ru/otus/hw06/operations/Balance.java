package ru.otus.hw06.operations;

import ru.otus.hw06.atm.ATM;
import ru.otus.hw06.money.Notes;
import ru.otus.hw09.AccountService;
import ru.otus.hw09.dao.Account;

import java.sql.Connection;
import java.util.Map;

public class Balance implements Operation {
    private float clientBalance;
    private Map<Notes, Integer> atmCashBox;
    private ATM atm;

    @Override
    public void execute(ATM atm) {
        this.atm = atm;
        Connection connection = atm.getConnection();
        AccountService accountService = new AccountService(connection);
        Account account = accountService.getAccount(atm.getClientCardId()).get();
        clientBalance = account.getRest();
        atmCashBox = atm.getAtmCashBox().getCashBox();
    }

    @Override
    public void printCheck() {
        System.out.println(atm);
        System.out.println("Your balance\n" + clientBalance + "\n" + "Available notes" + atmCashBox);
    }
}
