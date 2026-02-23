package stepdefinitions;

import form.GenericFormWrapper;
import form.LoginForm;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utilities.DriverManager;

public class Login {
    private final GenericFormWrapper<LoginForm> wrapper;
    public Login(){
        WebDriver driver= DriverManager.getDriver();
        this.wrapper= new GenericFormWrapper<>(new LoginForm(driver));
    }

    @Given("the user is on login page")
    public void theUserIsOnLoginPage() {
        wrapper.getForm().open();
    }

    @And("the user enter {string} in the login field {string}")
    public void theUserEnterInTheLoginField(String value, String fieldKey) {
        wrapper.getForm().fillField(fieldKey, value);
    }

    @And("the user click the Login button")
    public void theUserClickTheLoginButton() {
        wrapper.getForm().submit();
    }

    @Then("the user expect the login to {string}")
    public void theUserExpectTheLoginTo(String expectedResult) {
        LoginForm loginForm = wrapper.getForm();
        if ("success".equalsIgnoreCase(expectedResult)) {
            Assert.assertTrue("Login failed — success page not shown",
                    loginForm.isLoginSuccessful());
        } else {
            String error=loginForm.getLoginErrorMessage();
            Assert.assertFalse("Expected error message but empty",
                    error.isEmpty());
            System.out.println("Expected error message: " + error);
        }
    }
}
