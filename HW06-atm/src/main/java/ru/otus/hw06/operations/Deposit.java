package ru.otus.hw06.operations;

import ru.otus.hw06.atm.ATM;
import ru.otus.hw06.money.Notes;
import ru.otus.hw09.jdbc.service.AccountService;
import ru.otus.hw09.api.model.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class Deposit implements Operation {

    private int consumerCash;
    private float clientBalance;
    private ATM atm;
    private Map<Notes, Integer> consumerCashBundle;

    @Override
    public void execute(ATM atm) throws IllegalAccessException, NoSuchFieldException, SQLException {
        System.out.println("Depositing cash");
        Connection connection = atm.getConnection();
        AccountService accountService = new AccountService(connection);
        Account account = accountService.getAccount(atm.getClientCardId()).get();
        atm.setAtmCashBox(atm.getAtmCashBox().debit(atm.getConsumerCashBundle()));
        consumerCash = atm.getConsumerCashBundle().getBalance();
        account.setRest(account.getRest() + consumerCash);
        accountService.updateAccount(account);
        account = accountService.getAccount(atm.getClientCardId()).get();
        clientBalance = account.getRest();
        this.atm = atm;
        consumerCashBundle = atm.getConsumerCashBundle().getCashBox();

    }

    @Override
    public void printCheck() {
        System.out.println(atm);
        System.out.println("Cash deposit " + consumerCash + " was successful!");
        System.out.println(consumerCashBundle);
        System.out.println("Your balance\n" + clientBalance);
    }
}
