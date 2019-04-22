package com.otus.hw02;
import java.util.*;
import java.time.*;



public class CollectionsHW {
    public static void main(String[] args) {

        //Создаем коллекцию DIYarrayList и заполняем ее int-ми
        Random rnd = new Random();
        List<Integer> ls = new DIYarrayList<>();
        for (int i=0; i<30;i++){
            ls.add(rnd.nextInt(100));

        }


        printCollection(ls);
        System.out.println("collection size: "+ls.size());
        System.out.println("element number "+ 0 + " = "+ls.get(0));
        //ls.set(21, null);
        System.out.println("new element number "+ 21 + " = "+ls.get(21));
        printCollection(ls);
        System.out.println("element contains null: "+ls.contains(null)+", index: "+ls.indexOf(null));
        System.out.println("collection is empty "+ls.isEmpty());
        System.out.println("element remove 21: "+ ls.remove(0));
        Integer[] a = new Integer[10];
        ls.toArray(a);
        System.out.println("Массив: " + Arrays.toString(a));


    }
     public static void printCollection(List o){
        for (int i=0; i<o.size(); i++) {
            System.out.println(i + "   " + o.get(i));
        }
    }
}
