package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Driver Manager
 */
public class DriverManager {
    // ThreadLocal ensures each test gets its own browser instance
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger log = LoggerFactory.getLogger(DriverManager.class);
    /**
     * Gets the browser driver
     * Creates a new one if it doesn't exist
     */
    public static WebDriver getDriver() {
        log.info("Getting driver");
        if (driver.get() == null) {
            driver.set(createDriver());
            log.debug("Chrome driver session created: {}", driver);
        }else{
            log.debug("Reusing existing Chrome driver session");
        }
        return driver.get();
    }

    /**
     * Creates and configures the browser
     */
    private static WebDriver createDriver() {
        log.info("Creating new Chrome driver instance");
        // This automatically downloads the correct ChromeDriver
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
    /**
     * Closes the browser and cleans up
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            log.info("Closing Webdriver session");
            driver.get().quit();
            driver.remove();
        }else{
            log.warn("quitDriver() called but WebDriver was null");
        }
    }
}
