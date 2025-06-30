package com.ContactList.UI.pages;

import com.ContactList.UI.utils.factories.BasePageFactory;
import com.ContactList.UI.utils.BrowserManager;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;
    protected LoginPage loginPage;

    protected <T extends BasePage> T createInstance(Class<T> tClass) {
        return BasePageFactory.createInstance(page,tClass);
    }

    @BeforeAll
    public void setUp() {
        playwright = Playwright.create();
        browser = BrowserManager.initBrowser(playwright);
    }

    @AfterAll
    public void tearDown() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    public void initPage() {
        browserContext = browser.newContext();
        page = browserContext.newPage();
        loginPage = createInstance(LoginPage.class);
    }

    @AfterEach
    public void closePage() {
        page.close();
        browserContext.close();
    }
}
