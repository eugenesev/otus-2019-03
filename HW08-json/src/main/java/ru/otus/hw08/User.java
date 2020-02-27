package ru.otus.hw08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    Long id;
    String name;
    String[] strArr = {"q", "w", "e"};
    List list = new ArrayList();
    InnerObject object;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
        list.add(6);
        list.add("Six");
        list.add(new String[]{"a", "s", "d"});
        list.add(new InnerObject(1, "InnerObjectInList"));
        object = new InnerObject(2, "InnerObject");
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
                ", object=" + object +
                '}';
    }
}
