package com.otus.hw02;

import java.util.*;
import java.time.*;
import java.io.IOException;


public class CollectionsHW {
    public static void main(String[] args) {

//Создаем коллекцию DIYarrayList и заполняем ее int-ми
        Random rnd = new Random(1);
        List<Integer> ls = new DIYarrayList<>();
        List<Integer> secondLs = new DIYarrayList<>();
        List<Integer> thirdLs = new DIYarrayList<>();
        for (int i = 0; i < 30; i++) {
            ls.add(rnd.nextInt(1000));
            secondLs.add(rnd.nextInt(1000));
        }
//        ls.set(21, null);
//        System.out.println("new element number "+ 21 + " = "+ls.get(21));
//        printCollection(ls);
//        System.out.println("element contains null: "+ls.contains(null)+", index: "+ls.indexOf(null));
//        ls.remove(50);

// Проверка sort:
//        sortCheck(ls);

// Проверка copy:
//        copyCheck(secondLs, ls);


// Проверка addAll:
//        addAllCheck(ls, 24,11,32);


// Проверка subList() и containsAll():
//        subListContainsCheck(thirdLs,ls,17,27);


//-----------Другие методы-------------------------------------------


//        System.out.println("collection size: "+ls.size());
//
//        System.out.println("element number "+ 0 + " = "+ls.get(0));
//
//        ls.set(21, null);
//        System.out.println("new element number "+ 21 + " = "+ls.get(21));
//        printCollection(ls);
//        System.out.println("element contains null: "+ls.contains(null)+", index: "+ls.indexOf(null));
//
//        System.out.println("collection is empty "+ls.isEmpty());
//
//        printCollection(ls);
//        System.out.println("element remove 3: "+ ls.remove(3));
//        printCollection(ls);
//
//        printCollection(ls);
//        System.out.println("element remove 2: "+ ls.remove(ls.get(2)));
//        printCollection(ls);
//
//
//        Object[] a = ls.toArray();
//        System.out.println("Массив Object[] a: " + Arrays.toString(a));
//
//        Integer[] b = new Integer[10];
//        b=ls.toArray(b);
//        System.out.println("Массив " + b.getClass()+ " "+ Arrays.toString(b));
//
//        ls.add(4,25);
//        printCollection(ls);



    }

    public static void printCollection(List o) {
        for (int i = 0; i < o.size(); i++) {
            System.out.print(o.get(i) + " ");
        }
        System.out.println();
    }

    public static void sortCheck(List o) {
        System.out.println("---------Collections.sort()----------\nList:");
        printCollection(o);
        Collections.sort(o);
        System.out.println("Sorted list:");
        printCollection(o);
        System.out.println("--------------End-------------------");

    }

    public static void copyCheck(List dest, List src) {
        System.out.println("---------Collections.copy()----------\nFirst list:");
        printCollection(src);
        System.out.println("Second list:");
        printCollection(dest);
        Collections.copy(dest, src);
        System.out.println("Result list:");
        printCollection(dest);
        System.out.println("--------------End-------------------");
    }

    public static <T> void addAllCheck(List<T> o, T... args) {
        System.out.println("---------Collections.copy()----------\nList:");
        printCollection(o);
        Collections.addAll(o, args);
        System.out.println("List with added elements:");
        printCollection(o);
        System.out.println("--------------End-------------------");
    }

    public static void subListContainsCheck(List subList, List src, int fromIndex, int toIndex) {
        System.out.println("-----------subList()----------------\nList:");
        printCollection(src);
        subList = src.subList(fromIndex, toIndex);
        System.out.println("Sublist:");
        printCollection(subList);
        System.out.println("-----------containsAll()------------");
        containsAllCheck(src, subList);
        System.out.println("--------------End-------------------");
    }

    public static void containsAllCheck(List src, List subList) {
//        System.out.println("Changing element...");
//        subList.set(2, 501);
        if(src.containsAll(subList))
            System.out.println("Sublist is subcollection of list");
        else System.out.println("Sublist is not subcollection of list!");
    }

}
