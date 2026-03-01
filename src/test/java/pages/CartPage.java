package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    // IDs discovered from live app via uiautomator dump
    private static final String SCREEN_ID    = "cart screen";
    private static final String ITEM_ID      = "product row";
    private static final String REMOVE_ID    = "remove item";
    private static final String CHECKOUT_ID  = "Proceed To Checkout button";

    private final AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public boolean isCartScreenDisplayed() {
        try {
            return driver.findElement(AppiumBy.accessibilityId(SCREEN_ID)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getCartItemCount() {
        return driver.findElements(AppiumBy.accessibilityId(ITEM_ID)).size();
    }

    public void removeFirstItem() {
        List<WebElement> deletes = driver.findElements(AppiumBy.accessibilityId(REMOVE_ID));
        if (!deletes.isEmpty()) {
            deletes.get(0).click();
        }
    }

    public boolean isCartEmpty() {
        return driver.findElements(AppiumBy.accessibilityId(ITEM_ID)).isEmpty();
    }

    public void tapCheckout() {
        driver.findElement(AppiumBy.accessibilityId(CHECKOUT_ID)).click();
    }

    public void tapContinueShopping() {
        driver.navigate().back();
    }
}
