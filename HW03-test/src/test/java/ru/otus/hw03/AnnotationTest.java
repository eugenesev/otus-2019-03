package ru.otus.hw03;

public class AnnotationTest {

    private static FileRW fileRW;

    AnnotationTest() {
        System.out.println("AnnotationTest object created");
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("---------------Test before all tests---------------");
    }

    @Before
    public void beforeFileRWTest() {
        System.out.println("------BeforeEach------");
        fileRW = new FileRW("Hello");
    }

    @After
    void afterFileRWTest() {
        System.out.println("------AfterEach------");
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
    void methodTest2() {
        System.out.println("Test 2");
    }

}
