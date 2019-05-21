package ru.otus.hw03;

public class TestRunner {

//--------
    public static void run(Class<?>testClass) throws Exception {
// Выводим название класса-теста и получаем его конструктор по умолчанию
        System.out.println("---------------Testing " + testClass.toString() + "---------------");
        TestingContext testingContext = new TestingContext(testClass);
//--------

// Вызываем все методы помеченные аннотацией BeforeAll
        if (testingContext.invokeAnnotatedMethods(BeforeAll.class)) {
            Object testObj = testingContext.getTestObject();
            // Вызываем все методы помеченные аннотацией Before
            if (testingContext.invokeAnnotatedMethods(Before.class,testObj)) {
                // Вызываем все методы помеченные аннотацией Test
                testingContext.invokeAnnotatedMethods(Test.class,testObj);
            }
            // Вызываем все методы помеченные аннотацией After
            testingContext.invokeAnnotatedMethods(After.class,testObj);
        }
//--------

        // Вызываем все методы помеченные аннотацией AfterAll
        testingContext.invokeAnnotatedMethods(AfterAll.class);

//--------

    }
}
