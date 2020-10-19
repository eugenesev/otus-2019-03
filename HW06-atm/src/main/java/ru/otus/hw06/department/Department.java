package ru.otus.hw06.department;

import ru.otus.hw06.atm.ATM;
import ru.otus.hw06.atm.ATMImpl;
import ru.otus.hw06.money.ATMCashBox;
import ru.otus.hw06.money.ConsumerCashBundle;
import ru.otus.hw06.operations.Balance;
import ru.otus.hw06.operations.Deposit;
import ru.otus.hw06.operations.Withdraw;
import ru.otus.hw09.jdbc.service.AccountService;
import ru.otus.hw09.api.model.Account;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Department implements ATMDepartment {
    private static final String URL = "jdbc:h2:mem:";
    private AccountService accountService;
    private ATM atm;

    int counter;

    public int getCounter() {
        return counter;
    }


    public ATM getAtm() {
        return atm;
    }

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {

        ATMCashBox atmCashBox_1 = ATMCashBox.set()
                .fiveThousand(9)
                .twoThousand(10)
                .oneThousand(20)
                .fiveHundred(1)
                .twoHundred(0)
                .oneHundred(0)
                .fifty(0)
                .build();
        ATMCashBox atmCashBox_2 = ATMCashBox.set()
                .fiveThousand(1)
                .twoThousand(1)
                .oneThousand(1)
                .fiveHundred(1)
                .twoHundred(1)
                .oneHundred(1)
                .fifty(1)
                .build();

        ATMCashBox atmCashBox_3 = ATMCashBox.set()
                .fiveThousand(1)
                .twoThousand(1)
                .oneThousand(1)
                .fiveHundred(1)
                .twoHundred(1)
                .oneHundred(1)
                .fifty(1)
                .build();

        ATMCashBox atmCashBox_4 = ATMCashBox.set()
                .fiveThousand(1)
                .twoThousand(1)
                .oneThousand(1)
                .fiveHundred(1)
                .twoHundred(1)
                .oneHundred(1)
                .fifty(1)
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


        ATM atm1 = new ATMImpl(1, atmCashBox_1);
        ATM atm2 = new ATMImpl(2, atmCashBox_2);
        ATM atm3 = new ATMImpl(3, atmCashBox_3);
        ATM atm4 = new ATMImpl(4, atmCashBox_4);


        atm1.linkWith(atm2);
        atm2.linkWith(atm3);
        atm3.linkWith(atm4);

        Department department = new Department();
        department.init(atm1);
        department.getATMBalance();

        try (Connection connection = DriverManager.getConnection(URL)) {
            connection.setAutoCommit(false);

            department.accountService = new AccountService(connection);
            department.accountService.createTableAccount();
            department.accountService.saveAccount(new Account(0, "CardMir", 100_000f));
            department.accountService.saveAccount(new Account(0, "MasterCard", 5_000.50f));
            department.accountService.saveAccount(new Account(0, "CardMaestro", 123_456.7f));

            atm1.insertClientCard(2);
            atm1.setConnection(connection);
            atm1.choiceOperation(new Balance());
            atm1.putConsumerCashBundle(consumerCashBundle);
            atm1.choiceOperation(new Deposit());
            atm1.choiceOperation(new Balance());
            atm1.choiceOperation(new Withdraw());
            atm1.choiceOperation(new Balance());


        } catch (SQLException e) {
            e.printStackTrace();
        }

        department.getATMBalance();
        department.restoreATM();
        department.getATMBalance();

    }


    @Override
    public void init(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void restoreATM() {

        counter = atm.restore();
    }

    @Override
    public long getATMBalance() {
        long overallBalance = 0;
        ATM currentATM = atm;
        while (currentATM != null) {
            overallBalance += currentATM.getATMCashBoxBalance();
            currentATM = currentATM.getNextATM();
        }
        System.out.println("Overall balance: " + overallBalance);
        return overallBalance;
    }
}
