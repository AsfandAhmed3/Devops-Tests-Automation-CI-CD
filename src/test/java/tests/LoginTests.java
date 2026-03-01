package tests;

import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.BaseTest;
import utils.DriverManager;

public class LoginTests extends BaseTest {

    // Priority 1 — verify login screen is visible before doing anything else
    @Test(priority = 1)
    public void testLoginScreenIsDisplayedOnAppLaunch() {
        AndroidDriver driver = DriverManager.getDriver();
        LoginPage loginPage = new LoginPage(driver);

        Assert.assertTrue(loginPage.isLoginScreenDisplayed(),
                "Login screen should be displayed when app launches");
    }

    // Priority 2 — happy path: valid credentials → lands on Products screen
    @Test(priority = 2)
    public void testSuccessfulLoginWithValidCredentials() {
        AndroidDriver driver = DriverManager.getDriver();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("bob@example.com", "10203040");

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsScreenDisplayed(),
                "Products screen should be displayed after successful login");
    }

    // Priority 3 — leave username empty, enter valid password → username error shown
    @Test(priority = 3)
    public void testLoginWithEmptyUsernameShowsError() {
        AndroidDriver driver = DriverManager.getDriver();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("", "10203040");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed when username is empty");
    }

    // Priority 4 — enter valid username, leave password empty → password error shown
    @Test(priority = 4)
    public void testLoginWithEmptyPasswordShowsError() {
        AndroidDriver driver = DriverManager.getDriver();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("bob@example.com", "");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed when password is empty");
    }

    // Priority 5 — valid username, wrong password → generic error shown
    @Test(priority = 5)
    public void testLoginWithInvalidPasswordShowsError() {
        AndroidDriver driver = DriverManager.getDriver();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("bob@example.com", "wrongpassword");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid password");
        Assert.assertFalse(loginPage.getErrorMessage().isEmpty(),
                "Error message text should not be empty for invalid password");
    }

    //5 Test Cases Written Successfully for Login Functionality
}
