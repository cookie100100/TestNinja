package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.DriverManager;
import utilities.ScenarioData;

/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Hooks.
 */

public class Hooks {
    private static final Logger log = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setup(Scenario scenario) {
        log.info("=== Starting scenario: {} ===", scenario.getName());
        WebDriver driver = DriverManager.getDriver();
        log.debug("Clearing cookies to ensure logged-out session");
        driver.manage().deleteAllCookies();
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            log.warn("Test FAILED: " + scenario.getName());
        } else {
            log.info("Test PASSED: " + scenario.getName());
        }

        log.debug("Cleaning scenario state and closing driver...");
        ScenarioData.clear();
        DriverManager.quitDriver();
        log.info("=== Finished scenario: {} ===", scenario.getName());
    }
}