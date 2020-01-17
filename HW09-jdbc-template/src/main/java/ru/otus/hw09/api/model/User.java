package ru.otus.hw09.api.model;

import ru.otus.hw09.api.dao.Id;

public class User {

    @Id
    private final long id;
    private final String Name;
    private int Age;

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

    public void setAge(int age) {
        Age = age;
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
