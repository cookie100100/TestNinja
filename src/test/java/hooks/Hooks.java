package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DriverManager;

public class Hooks {

    WebDriver driver;

    @Before
    public void setup(Scenario scenario) {
        System.out.println("\n========================================");
        System.out.println("Starting Test: " + scenario.getName());
        System.out.println("========================================");

        // Initialize browser
        driver = DriverManager.getDriver();
    }

    @After
    public void teardown(Scenario scenario) {
        // If test failed, take a screenshot
        if (scenario.isFailed()) {
            System.out.println("Test FAILED: " + scenario.getName());
        } else {
            System.out.println("Test PASSED: " + scenario.getName());
        }

        System.out.println("========================================");
        System.out.println("Ending Test: " + scenario.getName());
        System.out.println("========================================\n");

        // Close browser
        DriverManager.quitDriver();
    }
}