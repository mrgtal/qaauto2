package page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Basic Page Class with common methods applicable to any inherited page class
 */
public class BasePage {

    /**
     * variable WebDriver type
     */
    public WebDriver webDriver;

    /**
     * BasePage class constructor
     *
     * @param webDriver WebDriver object
     */
    public BasePage(WebDriver webDriver) {

        this.webDriver = webDriver;
    }

    /**
     * Common method to get current Page URL
     *
     * @return String with current Page URL
     */
    public String getPageURL() {

        return webDriver.getCurrentUrl();
    }

    /**
     * Common method to get current Page title
     *
     * @return String with current Page title
     */
    public String getPageTitle() {

        return webDriver.getTitle();
    }

    /**
     * Waits until element is Displayed using specific max timeout
     *
     * @param element WebElement to wait for
     * @param timeout max timeout in seconds
     * @return WebElement after expected condition
     */
    public WebElement waitUntilElementDisplayed(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Checks if element is displayed within specific max timeout
     *
     * @param element WebElement to wait for
     * @param timeout max timeout in seconds
     * @return true/false value after expected condition
     */
    public boolean isElementDisplayed(WebElement element, int timeout) {
        try { waitUntilElementDisplayed(element, timeout).isDisplayed();

        }
        catch (TimeoutException e) {
            return false;
        }

        return true;
    }

    /**
     * Waits until element is Clickable using specific max timeout
     *
     * @param element WebElement to wait for
     * @param timeout max timeout in seconds
     * @return WebElement after expected condition
     */
    public WebElement waitUntilElementClicable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));

    }


}

