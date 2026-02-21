package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    // ThreadLocal ensures each test gets its own browser instance
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Gets the browser driver
     * Creates a new one if it doesn't exist
     */
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver());
        }
        return driver.get();
    }

    /**
     * Creates and configures the browser
     */
    private static WebDriver createDriver() {
        // This automatically downloads the correct ChromeDriver
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriver webDriver = new ChromeDriver();
        System.out.println("Chrome browser launched successfully");
        return webDriver;
    }
    /**
     * Closes the browser and cleans up
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            System.out.println("Browser closed successfully");
        }
    }
}
