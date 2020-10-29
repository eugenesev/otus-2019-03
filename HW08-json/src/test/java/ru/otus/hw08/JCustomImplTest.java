package ru.otus.hw08;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


public class JCustomImplTest {
    JCustom jCustom;
    Gson gson;

    @BeforeEach
    public void beforeTest() {
        this.jCustom = new JCustomImpl();
        this.gson = new Gson();
    }

    @Test
    public void nullToJson() {
        assertEquals(gson.toJson(null), jCustom.toJson(null));
    }

    @Test
    public void byteToJson() {
        assertEquals(gson.toJson((byte) 1), jCustom.toJson((byte) 1));
    }

    @Test
    public void shortToJson() {
        assertEquals(gson.toJson((short) 2f), jCustom.toJson((short) 2f));
    }

    @Test
    public void intToJson() {
        assertEquals(gson.toJson(3), jCustom.toJson(3));
    }

    @Test
    public void longToJson() {
        assertEquals(gson.toJson(4L), jCustom.toJson(4L));
    }

    @Test
    public void floatToJson() {
        assertEquals(gson.toJson(5f), jCustom.toJson(5f));
    }

    @Test
    public void doubleToJson() {
        assertEquals(gson.toJson(6d), jCustom.toJson(6d));
    }

    @Test
    public void stringToJson() {
        assertEquals(gson.toJson("aaa"), jCustom.toJson("aaa"));
    }

    @Test
    public void charToJson() {
        assertEquals(gson.toJson('b'), jCustom.toJson('b'));
    }

    @Test
    public void arrayToJson() {
        assertEquals(gson.toJson(new int[]{7, 8, 9}), jCustom.toJson(new int[]{7, 8, 9}));
    }

    @Test
    public void listToJson() {
        assertEquals(gson.toJson(List.of(10, 11, 12)), jCustom.toJson(List.of(10, 11, 12)));
    }

    @Test
    public void collectionsListToJson() {
        assertEquals(gson.toJson(Collections.singletonList(13)), jCustom.toJson(Collections.singletonList(13)));
    }

    @Test
    public void objectToJson() {
        User user = new User(1L, "Вася");
        assertEquals(gson.toJson(user), jCustom.toJson(user));
    }

    @Test
    public void objectToJsonInvert() {
        User user = new User(1L, "Вася");
        User jUser = gson.fromJson(jCustom.toJson(user), User.class);
        assertSame(user, jUser);
    }


}
