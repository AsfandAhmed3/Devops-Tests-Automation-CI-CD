package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

    private static final String SCREEN_ID   = "checkout address screen";
    private static final String FULLNAME_ID = "Full Name* input field";
    private static final String ZIPCODE_ID  = "Zip Code* input field";
    private static final String CONTINUE_ID = "To Payment button";

    private final AndroidDriver driver;

    public CheckoutPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public boolean isCheckoutScreenDisplayed() {
        try {
            return driver.findElement(AppiumBy.accessibilityId(SCREEN_ID)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterFullName(String fullName) {
        WebElement field = driver.findElement(AppiumBy.accessibilityId(FULLNAME_ID));
        field.clear();
        field.sendKeys(fullName);
    }

    public void enterZipCode(String zip) {
        WebElement field = driver.findElement(AppiumBy.accessibilityId(ZIPCODE_ID));
        field.clear();
        field.sendKeys(zip);
    }

    public void tapContinue() {
        try { driver.hideKeyboard(); } catch (Exception ignored) {}
        driver.findElement(AppiumBy.accessibilityId(CONTINUE_ID)).click();
    }

    public void fillAndSubmit(String fullName, String zip) {
        enterFullName(fullName);
        enterZipCode(zip);
        tapContinue();
    }
}
