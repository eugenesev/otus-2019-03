package ru.otus.hw08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    long id;
    String name;
    String[] strArr = {"q", "w", "e"};
    List list = new ArrayList();

    public User(long id, String name) {
        this.id = id;
        this.name = name;
        list.add(6);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", strArr=" + Arrays.toString(strArr) +
                ", list=" + list +
                '}';
    }

}
