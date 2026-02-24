package form;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Map;

/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Login Form.
 */

public class LoginForm extends GenericFormWrapper<LoginForm>{

    private static final String LOGIN_URL = "https://tutorialsninja.com/demo/index.php?route=account/login";
    private final Map<String, By> fieldMap=Map.of(
            "email", By.id("input-email"),
            "password", By.id("input-password")
    );
    private final By warningAlert=By.cssSelector(".alert-danger");
    private static final Logger log = LoggerFactory.getLogger(LoginForm.class);

    public LoginForm(WebDriver driver) {
        super(driver);
    }

    public void open(){
        log.debug("Opening login form");
        driver.get(LOGIN_URL);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        System.out.println("Login form opened");
    }

    public LoginForm fillField(String fieldName, String value) {
        return super.fillField(fieldMap, fieldName.toLowerCase(), value);
    }
    @Override
    public void submit() {
        log.debug("Submitting login form");
        driver.findElement(By.cssSelector("input[value='Login']")).click();
        System.out.println("Login submitted");
    }

    public String getPageWaringMessage() {
        return super.getPageWarningMessage(warningAlert,5);
    }

    public boolean isLoginSuccessful() {
        log.debug("Checking if login successful");
        return wait(Duration.ofSeconds(5)).until(driver -> {
            String url= driver.getCurrentUrl();
            log.debug("current url: {}", url);
            return url!=null && url.contains("route=account/account");
        });
    }
}