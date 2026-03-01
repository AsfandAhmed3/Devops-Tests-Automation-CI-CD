package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {

    // IDs discovered from live app via uiautomator dump
    private static final String SCREEN_ID    = "products screen";
    private static final String ITEM_ID      = "store item";
    private static final String SORT_BTN_ID  = "sort button";
    private static final String CART_BADGE_ID = "cart badge";

    private final AndroidDriver driver;

    public ProductsPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public boolean isProductsScreenDisplayed() {
        try {
            return driver.findElement(AppiumBy.accessibilityId(SCREEN_ID)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getProductCount() {
        return driver.findElements(AppiumBy.accessibilityId(ITEM_ID)).size();
    }

    public void tapFirstProduct() {
        List<WebElement> items = driver.findElements(AppiumBy.accessibilityId(ITEM_ID));
        if (!items.isEmpty()) {
            items.get(0).click();
        }
    }

    public void tapProductByIndex(int index) {
        driver.findElements(AppiumBy.accessibilityId(ITEM_ID)).get(index).click();
    }

    public void sortByNameAZ() {
        driver.findElement(AppiumBy.accessibilityId(SORT_BTN_ID)).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Name (A to Z)\"]")).click();
    }

    public void sortByNameZA() {
        driver.findElement(AppiumBy.accessibilityId(SORT_BTN_ID)).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Name (Z to A)\"]")).click();
    }

    public void sortByPriceAscending() {
        driver.findElement(AppiumBy.accessibilityId(SORT_BTN_ID)).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Price (ascending)\"]")).click();
    }

    public void sortByPriceDescending() {
        driver.findElement(AppiumBy.accessibilityId(SORT_BTN_ID)).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Price (descending)\"]")).click();
    }

    public boolean isCartBadgeDisplayed() {
        try {
            return driver.findElement(AppiumBy.accessibilityId(CART_BADGE_ID)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void tapCartIcon() {
        driver.findElement(AppiumBy.accessibilityId(CART_BADGE_ID)).click();
    }
}
