package form;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Registration Form.
 */

public class RegistrationForm extends GenericFormWrapper<RegistrationForm> {

    private static final String REGISTRATION_URL = "https://tutorialsninja.com/demo/index.php?route=account/register"; // change if needed

    private static final String LOGOUT_URL = "https://tutorialsninja.com/demo/index.php?route=account/logout";

    private static final Logger log = LoggerFactory.getLogger(RegistrationForm.class);
    // Field locators mapped by key
    private final Map<String, By> fieldMap = Map.of(
            "firstName", By.id("input-firstname"),
            "lastName", By.id("input-lastname"),
            "email", By.id("input-email"),
            "telephone", By.id("input-telephone"),
            "password", By.id("input-password"),
            "confirmPassword", By.id("input-confirm")
    );

    // Checkbox locators
    private final Map<String, By> checkboxMap = Map.of(
            "privacyPolicy", By.name("agree")
    );

    // Buttons / messages
    private final By submitButton = By.cssSelector("input[type='submit']");
    private final By successMessage = By.cssSelector("#content h1");
    private final By dangerMessage = By.cssSelector(".text-danger");
    private final By warningAlert = By.cssSelector(".alert.alert-danger");

    public RegistrationForm(WebDriver driver) {
        super(driver);
    }

    public void logout(){
        driver.get(LOGOUT_URL);
    }

    public void open() {
        log.debug("Opening form");
        driver.get(REGISTRATION_URL);
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    public RegistrationForm fillField(String fieldKey, String value) {
        return super.fillField(fieldMap, fieldKey, value);
    }

    public RegistrationForm check(String fieldKey) {
        log.debug("Checking field '{}'", fieldKey);
        By locator = checkboxMap.get(fieldKey);

        if (locator == null) {
            throw new IllegalArgumentException("Unknown checkbox: " + fieldKey);
        }

        WebElement checkbox = driver.findElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        return this;
    }

    public RegistrationForm uncheck(String fieldKey) {
        log.debug("Unchecking field '{}'", fieldKey);
        By locator = checkboxMap.get(fieldKey);
        if (locator == null) {
            throw new IllegalArgumentException("Unknown checkbox: " + fieldKey);
        }
        WebElement checkbox=driver.findElement(locator);
        if(checkbox.isSelected()){
            checkbox.click();
        }
        return this;
    }

    @Override
    public void submit() {
        log.debug("Submitting form");
        driver.findElement(submitButton).click();
    }

    public boolean isSuccessPageDisplayed(){
        log.debug("Checking if success page displayed");
        try{
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(d->d.getCurrentUrl().contains("route=account/success"));
        }catch (Exception e){
            return false;
        }
        return waitUntilVisible(successMessage, 10);
    }

    public String getPageWarningMessage(){
        return super.getPageWarningMessage(warningAlert, 5);
    }

    public List<String> getFieldErrorMessage(){
        waitUntilVisible(dangerMessage, 5);
        return driver.findElements(dangerMessage)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    protected boolean waitUntilVisible(By locator, int seconds){
        return super.waitUntilVisible(locator, seconds);
    }
}