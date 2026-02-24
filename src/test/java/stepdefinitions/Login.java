package stepdefinitions;

import form.LoginForm;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import utilities.DriverManager;
import utilities.ScenarioData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Login Step Definition
 */

public class Login {
    private LoginForm loginForm;
    private static final Logger log = LoggerFactory.getLogger(Login.class);

    @Given("I navigate to the login page")
    public void theUserIsOnLoginPage() {
        log.info("Navigating to the login page");
        WebDriver driver= DriverManager.getDriver();
        loginForm = new LoginForm(driver);
        loginForm.open();
    }

    @When("I log in with valid credentials")
    public void iLogInWithValidCredentials() {
        log.info("Logging in with valid credentials");
        var creds = ScenarioData.getCredentials();
        loginForm.fillField("email", creds.email());
        loginForm.fillField("password", creds.password());
        loginForm.submit();
    }
    @When("I log in with invalid credentials")
    public void iLogInWithInvalidCredentials() {
        log.info("Logging in with invalid credentials");
        loginForm.fillField("email", "wrong@test.com");
        loginForm.fillField("password", "wrongpassword");
        loginForm.submit();
    }
    @Then("I expect the login to {string}")
    public void theUserExpectTheLoginTo(String expectedResult) {
        if ("success".equalsIgnoreCase(expectedResult)) {
            boolean success=loginForm.isLoginSuccessful();
            log.info("Expecting login outcome = {}, actual success = {}", expectedResult, success);
            Assertions.assertTrue(
                    success,
                    "Login failed — success page not shown"
            );
        } else {
            String error=loginForm.getPageWaringMessage();
            log.info("Login error message is displayed:{}", error);
            Assertions.assertTrue(error.contains("No match for E-Mail Address and/or Password."), "Expected error message but empty");
        }
    }
}
