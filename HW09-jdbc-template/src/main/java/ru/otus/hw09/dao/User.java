package ru.otus.hw09.dao;

import ru.otus.hw09.Id;

public class User {

    @Id
    private final long id;
    private final String Name;
    private final int Age;

    public User(long id, String name, int age) {
        this.id = id;
        Name = name;
        Age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public int getAge() {
        return Age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Age=" + Age +
                '}';
    }
}
