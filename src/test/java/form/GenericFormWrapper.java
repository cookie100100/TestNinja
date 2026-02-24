package form;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Map;
/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Generic Form Wrapper for forms.
 */

public abstract class GenericFormWrapper<F extends GenericFormWrapper<F>>{
    protected final WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(GenericFormWrapper.class);

    protected GenericFormWrapper(WebDriver driver) {
        this.driver = driver;
    }
    @SuppressWarnings("unchecked")
    protected final F self(){
        return (F) this;
    }
    protected final WebDriverWait wait(Duration timeout) {
        return new WebDriverWait(driver, timeout);
    }


    protected final F fillField(Map<String, By> fieldMap, String fieldKey, String value) {
        log.debug("Filling field '{}'", fieldKey);
        By locator = fieldMap.get(fieldKey);
        waitUntilVisible(locator,5);
        if(locator == null){
            throw new IllegalArgumentException("Unknown field: " + fieldKey);
        }
        waitUntilVisible(locator, 10);
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(value);
        return self();
    }
    protected boolean waitUntilVisible(By locator, int seconds){
        log.debug("Waiting until visibility of element '{}'...", locator);
        try{
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(d-> {
                        try{
                            WebElement el = d.findElement(locator);
                            return el.isDisplayed();
                        } catch (NoSuchElementException | StaleElementReferenceException ignored){
                            return false;
                        }
                    });
            return true;
        }catch(TimeoutException ex){
            return false;
        }
    }
    protected final String getPageWarningMessage(By warningLocator, int seconds){
        log.debug("Getting page warning message '{}'...", warningLocator);
        waitUntilVisible(warningLocator, seconds);
        return driver.findElements(warningLocator)
                .stream()
                .filter(WebElement::isDisplayed)
                .map(e->e.getText().trim())
                .findFirst()
                .orElse("");
    }
    public abstract void submit();
}