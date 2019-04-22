package com.otus.hw02;
import java.util.*;
import java.time.*;
import java.io.IOException;



public class CollectionsHW {
    public static void main(String[] args) {

        //Создаем коллекцию DIYarrayList и заполняем ее int-ми
        Random rnd = new Random();
        List<Integer> ls = new DIYarrayList<>();
        List<Integer> arLs = new ArrayList<>();
        for (int i=0; i<30;i++){
            ls.add(rnd.nextInt(100));
            arLs.add(rnd.nextInt(100));

        }

        System.out.println("arLs");
        printCollection(ls);
        Collections.sort(ls);
        System.out.println("sorted arLs");
        printCollection(ls);



        System.out.println("collection size: "+ls.size());
        System.out.println("element number "+ 0 + " = "+ls.get(0));
        //ls.set(21, null);
        System.out.println("new element number "+ 21 + " = "+ls.get(21));
        printCollection(ls);
        System.out.println("element contains null: "+ls.contains(null)+", index: "+ls.indexOf(null));
        System.out.println("collection is empty "+ls.isEmpty());
        System.out.println("element remove 21: "+ ls.remove(0));
        Object[] a = ls.toArray();


        System.out.println("Массив: " + Arrays.toString(a));
        ls.remove(21);

        Integer[] b = new Integer[10];
       ls.toArray(b);
        System.out.println("Массив:3 " + Arrays.toString(b));



    }
     public static void printCollection(List o){
        for (int i=0; i<o.size(); i++) {
            System.out.println(i + "   " + o.get(i));
        }
    }
}
