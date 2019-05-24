package ru.otus.hw03.tests;

import ru.otus.hw03.FileRW;
import ru.otus.hw03.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRWTest {

    private static FileRW fileRW;

    public FileRWTest() {
        System.out.println("FileRWTest object created");
    }

    @BeforeAll
    public static void beforeAll() {
        //throw new UnsupportedOperationException();
        System.out.println("---Test 1 before all tests---");
    }

    @BeforeAll
    public static void beforeAll2() {
        //throw new UnsupportedOperationException();
        System.out.println("---Test 2 before all tests---");
    }

    @Before
    public void beforeFileRWTest() throws IOException {
        System.out.println("---BeforeEachTest---");
        List<Integer> numbers = new ArrayList<>(3);
        numbers.add(2);
        numbers.add(5);
        numbers.add(3);
        fileRW = new FileRW("Hello");
        fileRW.write(numbers);
    }

    @After
    public void afterFileRWTest() {
        System.out.println("---AfterEachTest---");
        fileRW.dispose();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("---Test 1 after all tests---");
    }

    @AfterAll
    public static void afterAll2() {
        System.out.println("---Test 2 after all tests---");
    }

    @Test
    public void methodTest() {
        System.out.println("Test 1");

    }

    @Test
    public void methodTest2() throws IOException {
        System.out.println("Test 2");
        fileRW.read();
    }

}
