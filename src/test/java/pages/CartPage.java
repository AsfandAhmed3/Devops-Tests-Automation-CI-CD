package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private AndroidDriver driver;

    @AndroidFindBy(accessibilityId = "test-Cart Content")
    private WebElement cartContent;

    @AndroidFindBy(accessibilityId = "test-Item")
    private List<WebElement> cartItems;

    @AndroidFindBy(accessibilityId = "test-CHECKOUT")
    private WebElement checkoutButton;

    @AndroidFindBy(accessibilityId = "test-CONTINUE SHOPPING")
    private WebElement continueShoppingButton;

    @AndroidFindBy(accessibilityId = "test-DELETE")
    private List<WebElement> deleteButtons;

    public CartPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public boolean isCartScreenDisplayed() {
        try {
            return cartContent.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public void removeFirstItem() {
        if (!deleteButtons.isEmpty()) {
            deleteButtons.get(0).click();
        }
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

    public void tapCheckout() {
        checkoutButton.click();
    }

    public void tapContinueShopping() {
        continueShoppingButton.click();
    }
}
