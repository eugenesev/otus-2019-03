package ru.otus.hw03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnnotationTest {

    private static FileRW fileRW;

    AnnotationTest() {
        System.out.println("AnnotationTest object created");
    }

    @BeforeAll
    public static void beforeAll() {
        //throw new UnsupportedOperationException();
        System.out.println("---------------Test before all tests---------------");
    }

    @Before
    public void beforeFileRWTest() throws IOException {
        System.out.println("------BeforeEachTest------");
        List<Integer> numbers = new ArrayList<>(3);
        numbers.add(2);
        numbers.add(5);
        numbers.add(3);
        fileRW = new FileRW("Hello");
        fileRW.write(numbers);
    }

    @After
    void afterFileRWTest() {
        System.out.println("------AfterEachTest------");
        fileRW.dispose();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("---------------Test 1 after all tests---------------");
    }

    @AfterAll
    public static void afterAll2() {
        System.out.println("---------------Test 2 after all tests---------------");
    }

    @Test
    void methodTest() {
        System.out.println("Test 1");

    }

    @Test
    void methodTest2() throws IOException {
        System.out.println("Test 2");
        fileRW.read();
    }

}
