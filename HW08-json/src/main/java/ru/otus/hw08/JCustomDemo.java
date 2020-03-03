package ru.otus.hw08;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.List;

public class JCustomDemo {
    public static void main(String[] args) throws IllegalAccessException {
        JCustom jCustom = new JCustomImpl();
        Gson gson = new Gson();

        User user = new User(1L, "Вася");

        System.out.println("jCustom:");
        System.out.println(jCustom.toJson(user));
        System.out.println("Gson:");
        System.out.println(gson.toJson(user));
        User jUser = gson.fromJson(jCustom.toJson(user), User.class);
        System.out.println(jUser);

        System.out.println(jCustom.toJson(null));
        System.out.println(gson.toJson(null));

        System.out.println(jCustom.toJson((byte)1));
        System.out.println(gson.toJson((byte)1));

        System.out.println(jCustom.toJson((short)2f));
        System.out.println(gson.toJson((short)2f));

        System.out.println(jCustom.toJson(3));
        System.out.println(gson.toJson(3));

        System.out.println(jCustom.toJson(4L));
        System.out.println(gson.toJson(4L));

        System.out.println(jCustom.toJson(5f));
        System.out.println(gson.toJson(5f));

        System.out.println(jCustom.toJson(6d));
        System.out.println(gson.toJson(6d));

        System.out.println(jCustom.toJson("aaa"));
        System.out.println(gson.toJson("aaa"));

        System.out.println(jCustom.toJson('b'));
        System.out.println(gson.toJson('b'));

        System.out.println(jCustom.toJson(new int[] {7, 8, 9}));
        System.out.println(gson.toJson(new int[] {7, 8, 9}));

        System.out.println(jCustom.toJson(List.of(10, 11 ,12)));
        System.out.println(gson.toJson(List.of(10, 11 ,12)));

        System.out.println(jCustom.toJson(Collections.singletonList(13)));
        System.out.println(gson.toJson(Collections.singletonList(13)));
    }
}
