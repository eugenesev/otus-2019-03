package ru.otus.hw06.atm;


import ru.otus.hw06.money.ATMCashBox;
import ru.otus.hw06.money.ConsumerCashBundle;
import ru.otus.hw06.operations.Balance;
import ru.otus.hw06.operations.Deposit;
import ru.otus.hw06.operations.Withdraw;
import ru.otus.hw09.api.model.Account;
import ru.otus.hw09.jdbc.service.AccountService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ATMRunner {

    private static final String URL = "jdbc:h2:mem:";

    public static void main(String[] args) throws IOException, IllegalAccessException, NoSuchFieldException, SQLException {

        ATMCashBox atmCashBox = ATMCashBox.set()
                .fiveThousand(9)
                .twoThousand(10)
                .oneThousand(20)
                .fiveHundred(1)
                .twoHundred(0)
                .oneHundred(0)
                .fifty(0)
                .build();


        ConsumerCashBundle consumerCashBundle = ConsumerCashBundle.set()
                .fiveThousand(1)
                .twoThousand(0)
                .oneThousand(3)
                .fiveHundred(0)
                .twoHundred(0)
                .oneHundred(3)
                .fifty(2)
                .build();

        try (Connection connection = DriverManager.getConnection(URL)) {
            connection.setAutoCommit(false);

            AccountService accountService = new AccountService(connection);
            accountService.createTableAccount();
            accountService.saveAccount(new Account(0, "CardMir", 100_000f));
            accountService.saveAccount(new Account(0, "MasterCard", 5_000.50f));
            accountService.saveAccount(new Account(0, "CardMaestro", 123_456.7f));

            ATMImpl atmImpl = new ATMImpl(1, atmCashBox);
            atmImpl.insertClientCard(2);
            atmImpl.setConnection(connection);
            atmImpl.choiceOperation(new Balance());
            atmImpl.putConsumerCashBundle(consumerCashBundle);
            atmImpl.choiceOperation(new Deposit());
            atmImpl.choiceOperation(new Balance());
            atmImpl.choiceOperation(new Withdraw());
            atmImpl.choiceOperation(new Balance());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
