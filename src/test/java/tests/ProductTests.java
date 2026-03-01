package tests;

import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductDetailPage;
import pages.ProductsPage;
import utils.BaseTest;
import utils.DriverManager;

import java.util.Map;

/**
 * Test cases 6–10: covers Products, Product Detail, Cart, and Checkout screens.
 * Overrides navigateToLogin() so each test starts on the Products screen,
 * not the Login screen.
 */
public class ProductTests extends BaseTest {

    private static final String APP_PACKAGE = "com.saucelabs.mydemoapp.rn";

    /**
     * Override BaseTest's @BeforeMethod: clear app and relaunch — lands on Products screen.
     * Does NOT navigate through the sidebar to Login.
     */
    @Override
    @BeforeMethod
    public void navigateToLogin() {
        DriverManager.getDriver().executeScript("mobile: clearApp", Map.of("appId", APP_PACKAGE));
        DriverManager.getDriver().activateApp(APP_PACKAGE);
        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}
    }

    // Test 6: Verify the products screen is displayed after app launch via sidebar
    @Test(priority = 6)
    public void testProductsScreenIsDisplayedOnAppLaunch() {
        AndroidDriver driver = DriverManager.getDriver();
        ProductsPage productsPage = new ProductsPage(driver);

        // App always opens on the Products screen after clearApp + activateApp
        Assert.assertTrue(productsPage.isProductsScreenDisplayed(),
                "Products screen should be displayed when app launches");
    }

    // Test 7: Verify product list contains at least one product
    @Test(priority = 7)
    public void testProductListIsNotEmpty() {
        AndroidDriver driver = DriverManager.getDriver();
        ProductsPage productsPage = new ProductsPage(driver);

        Assert.assertTrue(productsPage.getProductCount() > 0,
                "Products list should not be empty");
    }

    // Test 8: Verify tapping a product opens the product detail screen
    @Test(priority = 8)
    public void testTappingProductOpensDetailScreen() {
        AndroidDriver driver = DriverManager.getDriver();
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage detailPage = new ProductDetailPage(driver);

        productsPage.tapFirstProduct();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        Assert.assertTrue(detailPage.isProductDetailScreenDisplayed(),
                "Product detail screen should be displayed after tapping a product");
    }

    // Test 9: Verify adding a product to cart increases cart badge count
    @Test(priority = 9)
    public void testAddProductToCartUpdatesCartBadge() {
        AndroidDriver driver = DriverManager.getDriver();
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage detailPage = new ProductDetailPage(driver);

        productsPage.tapFirstProduct();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        detailPage.tapAddToCart();
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}

        // Navigate back to Products and confirm badge is visible
        driver.navigate().back();
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}

        Assert.assertTrue(productsPage.isCartBadgeDisplayed(),
                "Cart badge should appear after adding a product to the cart");
    }

    // Test 10: Verify cart screen displays the added item
    @Test(priority = 10)
    public void testCartShowsAddedProduct() {
        AndroidDriver driver = DriverManager.getDriver();
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage detailPage = new ProductDetailPage(driver);
        CartPage cartPage = new CartPage(driver);

        // Add a product to cart
        productsPage.tapFirstProduct();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        detailPage.tapAddToCart();
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}

        // Go back to products, then open cart
        driver.navigate().back();
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        productsPage.tapCartIcon();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        Assert.assertTrue(cartPage.isCartScreenDisplayed(),
                "Cart screen should be displayed after tapping the cart icon");
        Assert.assertTrue(cartPage.getCartItemCount() > 0,
                "Cart should contain at least one item after adding a product");
    }
}
