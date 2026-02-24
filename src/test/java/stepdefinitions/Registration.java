package stepdefinitions;

import form.GenericFormWrapper;
import form.RegistrationForm;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.DriverManager;
import utilities.ScenarioData;
import validators.impl.*;

import java.util.List;

/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Registration Step Definition
 */

public class Registration {
    private RegistrationForm registrationForm;
    private static final Logger log = LoggerFactory.getLogger(Registration.class);

    @Given("I navigate to the registration page")
    public void iNavigateToTheRegistrationPage() {
        log.info("Navigating to the registration page");
        WebDriver driver = DriverManager.getDriver();
        registrationForm=new RegistrationForm(driver);
        registrationForm.open();
    }
    @When("I register a fresh account with valid data")
    public void iRegisterAFreshAccountWithValidData() {
        log.info("Registering a fresh account with valid data");
        String email="cred"+System.currentTimeMillis()+"@test.com";
        String password = "cred"+System.currentTimeMillis() % 100000;
        registrationForm.fillField("firstName","Testcred");
        registrationForm.fillField("lastName","Test");
        registrationForm.fillField("email",email);
        registrationForm.fillField("telephone","123456`");
        registrationForm.fillField("password",password);
        registrationForm.fillField("confirmPassword",password);
        registrationForm.check("privacyPolicy");
        registrationForm.submit();
        Assertions.assertTrue(registrationForm.isSuccessPageDisplayed(),
                "Registration failed - cannot proceed to login");
        ScenarioData.setCredentials(email, password);
    }

    @And("I log out")
    public void iLogOut() {
        log.info("Logging out");
        registrationForm.logout();
    }

    @Then("the user expect the registration to {string}")
    public void theUserExpectTheRegistrationTo(String expectedResult) {
        if ("success".equalsIgnoreCase(expectedResult)) {
            boolean success = registrationForm.isSuccessPageDisplayed();
            log.info("Expecting registration outcome = {}, actual success = {}", expectedResult, success);
            Assertions.assertTrue(success, "Expected SUCCESS but did not see success message.");
        } else {
            String pageWarning = registrationForm.getPageWarningMessage();
            var fieldErrors = registrationForm.getFieldErrorMessage();
            log.info("Expecting registration outcome = {}", expectedResult);
            log.info("Register error message is displayed:{}, {}", pageWarning, fieldErrors);
            Assertions.assertTrue(!pageWarning.isEmpty() || !fieldErrors.isEmpty(), "Expected ERROR but did not see error message.");
        }
    }

    @Then("all registration validations should show correct error messages")
    public void allRegistrationValidationsShouldShowCorrectErrorMessages() {
        log.info("Validating registration show correct error messages");
        var validators = List.of(
                new FirstNameLengthValidator(),
                new EmptyFirstNameValidator(),
                new LastNameLengthValidator(),
                new EmptyLastNameValidator(),
                new EmailFormatValidator(),
                new EmptyEmailValidator(),
                new DuplicateEmailValidator(),
                new TelephoneLengthValidator(),
                new EmptyTelephoneValidator(),
                new PasswordLengthValidator(),
                new PasswordTooLongValidator(),
                new EmptyPasswordValidator(),
                new PasswordConfirmMatchValidator(),
                new EmptyConfirmPasswordValidator(),
                new PrivacyPolicyUncheckValidator()
        );
        new validators.RegistrationValidationRunner(registrationForm, validators).runAll();
    }
}