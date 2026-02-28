package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ProductDetailPage {

    private AndroidDriver driver;

    @AndroidFindBy(accessibilityId = "test-Description")
    private WebElement productDescription;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
    private WebElement productName;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]")
    private WebElement productPrice;

    @AndroidFindBy(accessibilityId = "test-ADD TO CART")
    private WebElement addToCartButton;

    @AndroidFindBy(accessibilityId = "test-BACK TO PRODUCTS")
    private WebElement backButton;

    public ProductDetailPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public boolean isProductDetailScreenDisplayed() {
        try {
            return productDescription.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public void tapAddToCart() {
        addToCartButton.click();
    }

    public void tapBackToProducts() {
        backButton.click();
    }
}
