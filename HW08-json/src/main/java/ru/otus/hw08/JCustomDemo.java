package ru.otus.hw08;

import com.google.gson.Gson;

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

    }
}
