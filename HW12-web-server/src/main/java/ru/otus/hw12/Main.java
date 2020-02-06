package ru.otus.hw12;


import ru.otus.hw12.db.DbStarter;
import ru.otus.hw12.db.DbStarterImpl;

public class Main {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) {
        DbStarter dbStarter = new DbStarterImpl();
        dbStarter.start();
    }
}
