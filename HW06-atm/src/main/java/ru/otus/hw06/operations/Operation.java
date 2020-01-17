package ru.otus.hw06.operations;

import ru.otus.hw06.atm.ATM;

import java.io.IOException;
import java.sql.SQLException;

public interface Operation {

    void execute(ATM atm) throws IOException, IllegalAccessException, NoSuchFieldException, SQLException;

    void printCheck();

}
