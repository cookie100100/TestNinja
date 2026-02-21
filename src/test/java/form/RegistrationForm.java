package form;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class RegistrationForm {

    private final WebDriver driver;

    private static final String REGISTRATION_URL = "https://tutorialsninja.com/demo/index.php?route=account/register"; // change if needed

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

    public RegistrationForm(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
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

    public void submit() {
        driver.findElement(submitButton).click();
    }

    public boolean isMessageDisplayed(String type) {
        return switch (type.toLowerCase()) {
            case "success" -> isDisplayed(successMessage);
            case "error" -> isDisplayed(errorMessage);
            default -> throw new IllegalArgumentException("Unknown message type: " + type);
        };
    }

    private boolean isDisplayed(By locator) {
        return driver.findElements(locator)
                .stream()
                .anyMatch(WebElement::isDisplayed);
    }
}