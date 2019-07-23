package ru.otus;

public class ClassImpl implements ClassInterface {

    @Override
    public void calculation(int param) {
        System.out.println("Calculation()");
    }


    @Override
    public int calculation(int param1, int param2){
        return param1*param2;
    }

}
