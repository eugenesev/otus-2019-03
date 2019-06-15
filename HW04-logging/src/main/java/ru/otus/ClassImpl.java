package ru.otus;

public class ClassImpl implements ClassInterface {

    @LOG
    @Override
    public void calculation(int param) {
        System.out.println("Calculation()");
    }
}
