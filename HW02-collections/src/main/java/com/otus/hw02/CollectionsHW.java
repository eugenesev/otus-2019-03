package com.otus.hw02;
import java.util.*;
import java.time.*;
import java.io.IOException;



public class CollectionsHW {
    public static void main(String[] args) {

//Создаем коллекцию DIYarrayList и заполняем ее int-ми
        Random rnd = new Random();
        List<Integer> ls = new DIYarrayList<>();
        List<Integer> secondLs = new DIYarrayList<>();
        List<Integer> thirdLs = new DIYarrayList<>();
        for (int i=0; i<30;i++){
            ls.add(rnd.nextInt(1000));
            secondLs.add(rnd.nextInt(1000));
        }


// Проверка sort:

//        System.out.println("ls");
//        printCollection(ls);
//        Collections.sort(ls);
//        System.out.println("sorted ls");
//        printCollection(ls);

//Проверка copy:

//        System.out.println("ls");
//        printCollection(ls);
//        System.out.println("secondls");
//        printCollection(secondLs);
//        Collections.copy(secondLs, ls);
//        System.out.println("secondLs 2");
//        printCollection(secondLs);

//Проверка addAll:

//        Collections.addAll(ls, 24,11,32);
//        printCollection(ls);


//-----------Другие методы-------------------------------------------


//        System.out.println("collection size: "+ls.size());

//        System.out.println("element number "+ 0 + " = "+ls.get(0));

//        ls.set(21, null);
//        System.out.println("new element number "+ 21 + " = "+ls.get(21));
//        printCollection(ls);
//        System.out.println("element contains null: "+ls.contains(null)+", index: "+ls.indexOf(null));

//        System.out.println("collection is empty "+ls.isEmpty());

//        printCollection(ls);
//        System.out.println("element remove 3: "+ ls.remove(3));
//        printCollection(ls);

//        printCollection(ls);
//        System.out.println("element remove 2: "+ ls.remove(ls.get(2)));
//        printCollection(ls);


//        Object[] a = ls.toArray();
//        System.out.println("Массив Object[] a: " + Arrays.toString(a));

//        Integer[] b = new Integer[10];
//        b=ls.toArray(b);
//        System.out.println("Массив " + b.getClass()+ " "+ Arrays.toString(b));
//
//        ls.add(4,25);
//        printCollection(ls);

//        System.out.println("ls");
//        printCollection(ls);
//        thirdLs=ls.subList(3,10);
//        System.out.println("thirdLs");
//        printCollection(thirdLs);

//        System.out.println("ls");
//        printCollection(ls);
//        thirdLs=ls.subList(3,10);
//        System.out.println("thirdLs");
//        printCollection(thirdLs);
//        System.out.println("thirdLs is subcollection of ls: " + ls.containsAll(thirdLs));



    }
     public static void printCollection(List o){
        for (int i=0; i<o.size(); i++) {
            System.out.println(i + "   " + o.get(i));
        }
    }
}
