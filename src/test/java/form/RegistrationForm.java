package form;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
    private final By pageWarning = By.cssSelector(".alert.alert-danger");

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

    public String getPageErrorMessage(){
        return driver.findElements(pageWarning).stream()
                .filter(WebElement::isDisplayed)
                .map(e->e.getText().trim())
                .findFirst()
                .orElse("");
    }

    public String getFieldErrorMessage(String fieldKey){
        By inputLocator = fieldMap.get(fieldKey);
        if(inputLocator == null) throw new IllegalArgumentException("Unknown field: " + fieldKey);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d->!d.findElements(inputLocator).isEmpty());
        WebElement input=driver.findElement(inputLocator);
        String inputId=input.getAttribute("id");
        By errorLocator=By.xpath("//input[@id='"+inputId+"']/following-sibling::div[contains(@class,'text-danger')]");
        return driver.findElements(errorLocator).stream()
                .filter(WebElement::isDisplayed)
                .map(e->e.getText().trim())
                .findFirst()
                .orElse("");
    }
}