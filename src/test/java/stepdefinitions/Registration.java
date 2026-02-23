package stepdefinitions;

import form.GenericFormWrapper;
import form.RegistrationForm;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utilities.DriverManager;
import validators.impl.*;

import java.util.List;

/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Registration Step Definition
 */

public class Registration {
    private GenericFormWrapper<RegistrationForm> wrapper;


    @Given("the user is on registration page")
    public void theUserIsOnRegistrationPage() {
        WebDriver driver= DriverManager.getDriver();
        wrapper = new GenericFormWrapper<>(new RegistrationForm(driver));
        wrapper.getForm().open();
    }

    @And("the user enter {string} in the register field {string}")
    public void theUserEnterInTheRegisterField(String value, String fieldKey) {
        if("AUTO_EMAIL".equals(value) && "email".equals(fieldKey)){
            value="test" + System.currentTimeMillis()+"@test.com";
        }
        String actualValue = value.equals("\"\"")? "": value.replace("\"", "");
        wrapper.getForm().fillField(fieldKey, actualValue);
    }

    @And("the user agree to the terms and conditions")
    public void theUserAgreeToTheTermsAndConditions() {
        wrapper.getForm().check("privacyPolicy");
    }

    @And("the user click the Continue button")
    public void theUserClickTheContinueButton() {
        wrapper.getForm().submit();
    }

    @Then("the user expect the registration to {string}")
    public void theUserExpectTheRegistrationTo(String expectedResult) {
        RegistrationForm registrationForm=wrapper.getForm();
        if ("success".equalsIgnoreCase(expectedResult)) {
            Assert.assertTrue("Expected SUCCESS but did not see success message.",
                    registrationForm.isMessageDisplayed("success"));
        } else {
            Assert.assertTrue("Expected ERROR but did not see error message.",
                    registrationForm.isMessageDisplayed("error"));
        }
    }

    @Then("all registration validations should show correct error messages")
    public void allRegistrationValidationsShouldShowCorrectErrorMessages() {
        RegistrationForm form=wrapper.getForm();
        var validators = List.of(
                new FirstNameLengthValidator(),
                new LastNameLengthValidator(),
                new EmailFormatValidator(),
                new DuplicateEmailValidator(),
                new TelephoneLengthValidator(),
                new PasswordLengthValidator(),
                new PasswordConfirmMatchValidator(),
                new PrivacyPolicyUncheckValidator()
        );
        new validators.RegistrationValidationRunner(form, validators).runAll();
    }
}