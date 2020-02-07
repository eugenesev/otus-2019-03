package ru.otus.hw12.db;

import ru.otus.hw10.api.service.DBServiceUser;

public interface DbStarter {
    void start();
    DBServiceUser getDBServiceUser();
}
