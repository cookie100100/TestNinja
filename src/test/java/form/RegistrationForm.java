package form;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class RegistrationForm {

    private final WebDriver driver;

    private static final String REGISTRATION_URL = "https://tutorialsninja.com/demo/index.php?route=account/register"; // change if needed

    private static final String LOGOUT_URL = "https://tutorialsninja.com/demo/index.php?route=account/logout";
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
    private final By errorMessage = By.cssSelector(".error");
    private final By dangerMessage = By.cssSelector(".text-danger");
    private final By pageWarning = By.cssSelector(".alert.alert-danger");

    public RegistrationForm(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(LOGOUT_URL);
        driver.get(REGISTRATION_URL);
        driver.manage().window().maximize();
        System.out.println("Registration form opened");
    }

    public void fillField(String fieldKey, String value) {
        By locator = fieldMap.get(fieldKey);

        if (locator == null) {
            throw new IllegalArgumentException("Unknown field: " + fieldKey);
        }

        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    public void check(String fieldKey) {
        By locator = checkboxMap.get(fieldKey);

        if (locator == null) {
            throw new IllegalArgumentException("Unknown checkbox: " + fieldKey);
        }

        WebElement checkbox = driver.findElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void uncheck(String fieldKey) {
        By locator = checkboxMap.get(fieldKey);
        if (locator == null) {
            throw new IllegalArgumentException("Unknown checkbox: " + fieldKey);
        }
        WebElement checkbox=driver.findElement(locator);
        if(checkbox.isSelected()){
            checkbox.click();
        }
    }

    public void submit() {
        driver.findElement(submitButton).click();
    }

    public boolean isMessageDisplayed(String type) {
        return switch (type.toLowerCase()) {
            case "success" -> isSuccessPageDisplayed();
            case "error" -> isDisplayed(errorMessage);
            default -> throw new IllegalArgumentException("Unknown message type: " + type);
        };
    }

    private boolean isDisplayed(By locator) {
        waitUntilVisible(locator, 5);
        return driver.findElements(locator)
                .stream()
                .anyMatch(WebElement::isDisplayed);
    }

    private boolean isSuccessPageDisplayed(){
        waitUntilVisible(successMessage, 5);
        return driver.getCurrentUrl().contains("route=account/success");
    }

    public String getPageErrorMessage(){
        waitUntilVisible(pageWarning, 5);
        return driver.findElements(pageWarning).stream()
                .filter(WebElement::isDisplayed)
                .map(e->e.getText().trim())
                .findFirst()
                .orElse("");
    }

    public List<String> getFieldErrorMessage(){
        waitUntilVisible(dangerMessage, 5);
        return driver.findElements(dangerMessage)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    private boolean waitUntilVisible(By locator, int seconds){
        try{
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(d->d.findElements(locator)
                            .stream()
                            .anyMatch(WebElement::isDisplayed));
            return true;
        }catch(TimeoutException ex){
            return false;
        }
    }
}