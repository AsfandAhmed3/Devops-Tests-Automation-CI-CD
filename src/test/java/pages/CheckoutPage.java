package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

    // IDs discovered from live app via uiautomator dump
    // Note: this app uses Full Name (combined), not separate first/last name fields
    private static final String FULL_NAME_ID  = "Full Name* input field";
    private static final String ADDRESS_ID    = "Address Line 1* input field";
    private static final String ZIP_ID        = "Zip Code* input field";
    private static final String CONTINUE_ID   = "To Payment button";
    private static final String ERROR_XPATH   =
            "//*[@content-desc='generic-error-message']//android.widget.TextView";
    private static final String COMPLETE_XPATH =
            "//android.widget.TextView[@text='Thank you for your order!']";

    private final AndroidDriver driver;

    public CheckoutPage(AndroidDriver driver) {
        this.driver = driver;
    }

    /** Sets Full Name field (app combines first+last into one field) */
    public void enterFirstName(String firstName) {
        WebElement field = driver.findElement(AppiumBy.accessibilityId(FULL_NAME_ID));
        field.clear();
        field.sendKeys(firstName);
    }

    /** Appends last name to Full Name field */
    public void enterLastName(String lastName) {
        WebElement field = driver.findElement(AppiumBy.accessibilityId(FULL_NAME_ID));
        field.sendKeys(" " + lastName);
    }

    public void enterZipCode(String zipCode) {
        WebElement field = driver.findElement(AppiumBy.accessibilityId(ZIP_ID));
        field.clear();
        field.sendKeys(zipCode);
    }

    public void fillCheckoutForm(String firstName, String lastName, String zipCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipCode);
    }

    public void tapContinue() {
        driver.hideKeyboard();
        driver.findElement(AppiumBy.accessibilityId(CONTINUE_ID)).click();
    }

    public void tapFinish() {
        // Finish button appears on the order review screen after tapContinue()
        driver.findElement(By.xpath("//android.widget.TextView[@text='Place Order']")).click();
    }

    public boolean isOrderCompleted() {
        try {
            return driver.findElement(By.xpath(COMPLETE_XPATH)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(By.xpath(ERROR_XPATH)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return driver.findElement(By.xpath(ERROR_XPATH)).getText();
    }
}
