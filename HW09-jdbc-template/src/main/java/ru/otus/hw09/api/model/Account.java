package ru.otus.hw09.api.model;

import ru.otus.hw09.api.dao.Id;

public class Account {

    @Id
    private final long no;
    private final String type;
    private float rest;

    public Account(long no, String type, float rest) {
        this.no = no;
        this.type = type;
        this.rest = rest;
    }

    public long getNo() {
        return no;
    }

    public String getType() {
        return type;
    }

    public float getRest() {
        return rest;
    }

    public void setRest (float rest) {
        this.rest = rest;
    }

    @Override
    public String toString() {
        return "Account{" +
                "no=" + no +
                ", type='" + type + '\'' +
                ", rest=" + rest +
                '}';
    }
}
