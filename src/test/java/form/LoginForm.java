package form;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginForm {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String LOGIN_URL = "https://tutorialsninja.com/demo/index.php?route=account/login";
    private final By errorAlert=By.cssSelector(".alert-danger");

    public LoginForm(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void open(){
        driver.get(LOGIN_URL);
        driver.manage().window().maximize();
        System.out.println("Login form opened");
    }

    public void fillField(String fieldName, String value) {
        By locator = switch(fieldName.toLowerCase()) {
            case "email" -> By.id("input-email");
            case "password" -> By.id("input-password");
            default -> throw new IllegalArgumentException("Unknown field: " + fieldName);
        };
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
        System.out.println("Field filled");
    }

    public void submit() {
        driver.findElement(By.cssSelector("input[value='Login']")).click();
        System.out.println("Login submitted");
    }

    public String getLoginErrorMessage() {
        wait.until(driver -> !driver.findElements(errorAlert).isEmpty());
        return driver.findElement(errorAlert).getText();
    }

    public boolean isLoginSuccessful() {
        return wait.until(driver -> driver.getCurrentUrl().contains("route=account/account"));
    }
}