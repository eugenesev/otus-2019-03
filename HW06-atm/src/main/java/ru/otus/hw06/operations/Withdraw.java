package ru.otus.hw06.operations;

import ru.otus.hw06.atm.ATM;
import ru.otus.hw06.money.Notes;
import ru.otus.hw09.jdbc.service.AccountService;
import ru.otus.hw09.api.model.Account;

import java.io.*;
import java.sql.Connection;
import java.util.Map;

public class Withdraw implements Operation {

    private int consumerCash;
    private float clientBalance;
    private int consumerBalance;
    private Map<Notes, Integer> consumerCashBundle;
    private ATM atm;

    @Override
    public void execute(ATM atm) throws IOException {
        System.out.println("Withdrawing cash");

        int atmBalance = atm.getAtmCashBox().getBalance();
        int cashValue = WithdrawValueAsker.getWithdrawValueFromUser(new WithdrawValueAsker(System.in, System.out));
        if (atmBalance - cashValue >= 0) {
            if (cashValue % 50 == 0) {
                Connection connection = atm.getConnection();
                AccountService accountService = new AccountService(connection);
                Account account = accountService.getAccount(atm.getClientCardId()).get();
                clientBalance = account.getRest();
                if (clientBalance - cashValue >= 0){
                    atm.setAtmCashBox(atm.getAtmCashBox().credit(cashValue));
                    atm.putConsumerCashBundle(atm.getAtmCashBox().getConsumerCashBundle());
                    consumerCash = atm.getConsumerCashBundle().getBalance();
                    account.setRest(account.getRest() - consumerCash);
                    accountService.updateAccount(account);
                    account = accountService.getAccount(atm.getClientCardId()).get();
                    clientBalance = account.getRest();
                }else {
                    System.out.println("There is not enough money!");
                    atm.choiceOperation(new Withdraw());
                }
            } else {
                System.out.println("Value must be a multiple of 50");
                atm.choiceOperation(new Withdraw());
            }
        } else {
            System.out.println("There is not enough money in the ATM");
        }
        this.atm = atm;
        consumerCashBundle = atm.getConsumerCashBundle().getCashBox();
//        consumerBalance = atm.getConsumerCashBundle().getBalance();
    }

    @Override
    public void printCheck() {
        System.out.println(atm);
        System.out.println("Received money: \n" + consumerCashBundle);
        System.out.println("Withdraw " + consumerCash + " RUB");
        System.out.println("Your balance: " + clientBalance + " RUB");
    }


}