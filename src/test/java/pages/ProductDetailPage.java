package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ProductDetailPage {

    // IDs confirmed via uiautomator dump on live device
    private static final String SCREEN_ID   = "product screen";
    private static final String PRICE_ID    = "product price";
    private static final String ADD_CART_ID = "Add To Cart button";
    private static final String NAME_XPATH  =
            "//*[@content-desc='product screen']//android.widget.TextView[1]";

    private final AndroidDriver driver;

    public ProductDetailPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public boolean isProductDetailScreenDisplayed() {
        try {
            return driver.findElement(AppiumBy.accessibilityId(SCREEN_ID)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getProductName() {
        return driver.findElement(By.xpath(NAME_XPATH)).getText();
    }

    public String getProductPrice() {
        return driver.findElement(AppiumBy.accessibilityId(PRICE_ID)).getText();
    }

    public void tapAddToCart() {
        driver.findElement(AppiumBy.accessibilityId(ADD_CART_ID)).click();
    }

    public void tapBackToProducts() {
        driver.navigate().back();
    }
}
