package com.ContactList.UI.testSupport;

import com.ContactList.UI.driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    @BeforeEach
    public void setup() {
        DriverManager.init();
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quit();
    }
}
