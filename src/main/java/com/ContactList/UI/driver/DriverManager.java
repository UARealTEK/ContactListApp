package com.ContactList.UI.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    private DriverManager(){}

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return options;
    }

    public static void init() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = getChromeOptions();

            WebDriver driver1 = new ChromeDriver(options);
            driver1.manage().window().maximize();
            driver.set(driver1);
            WebDriverWait wait1 = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
            wait.set(wait1);
        }
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            init();
        }
        return driver.get();
    }

    public static WebDriverWait getWait() {
        if (wait.get() == null) {
            init();
        }
        return wait.get();
    }

    public static void quit() {
        if(driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }

        if (wait.get() != null) {
            wait.remove();
        }
    }
}
