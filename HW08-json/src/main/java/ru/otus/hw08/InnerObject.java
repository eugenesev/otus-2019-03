package ru.otus.hw08;

public class InnerObject {
    private int id;
    private String stringField;

    public InnerObject(int id, String stringField) {
        this.id = id;
        this.stringField = stringField;
    }

    @Override
    public String toString() {
        return "InnerObject{" +
                "intField=" + id +
                ", stringField='" + stringField + '\'' +
                '}';
    }
}
