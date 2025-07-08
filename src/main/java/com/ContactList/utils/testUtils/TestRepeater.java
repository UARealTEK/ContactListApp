package com.ContactList.utils.testUtils;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * custom test repeater that may be used for running flaky Tests
 */
public class TestRepeater {

    public static void repeatTest(Method testName, Runnable testLogic) {
        int iteration = 0;

        while (true) {
            iteration++;

            try {
                System.out.println("============");
                System.out.println("Started attempt" + iteration);
                System.out.println("============");
                testLogic.run();
            } catch (AssertionError | Exception e) {
                System.out.println("❌ Test [" + testName + "] failed on iteration " + iteration);
                fail("Test [" + testName + "] failed on iteration " + iteration + ": " + e.getMessage());
                break;
            }
        }
    }
}
