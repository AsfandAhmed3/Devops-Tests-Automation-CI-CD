package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class ProductsPage {

    private AndroidDriver driver;

    @AndroidFindBy(accessibilityId = "test-PRODUCTS")
    private WebElement productsHeader;

    @AndroidFindBy(accessibilityId = "test-Item")
    private List<WebElement> productItems;

    @AndroidFindBy(accessibilityId = "test-Modal Selector Button")
    private WebElement sortButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Name (A to Z)\"]")
    private WebElement sortByNameAZ;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Name (Z to A)\"]")
    private WebElement sortByNameZA;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Price (ascending)\"]")
    private WebElement sortByPriceAsc;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Price (descending)\"]")
    private WebElement sortByPriceDesc;

    public ProductsPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public boolean isProductsScreenDisplayed() {
        try {
            return productsHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getProductCount() {
        return productItems.size();
    }

    public void tapFirstProduct() {
        if (!productItems.isEmpty()) {
            productItems.get(0).click();
        }
    }

    public void tapProductByIndex(int index) {
        productItems.get(index).click();
    }

    public void sortByNameAZ() {
        sortButton.click();
        sortByNameAZ.click();
    }

    public void sortByNameZA() {
        sortButton.click();
        sortByNameZA.click();
    }

    public void sortByPriceAscending() {
        sortButton.click();
        sortByPriceAsc.click();
    }

    public void sortByPriceDescending() {
        sortButton.click();
        sortByPriceDesc.click();
    }
}
