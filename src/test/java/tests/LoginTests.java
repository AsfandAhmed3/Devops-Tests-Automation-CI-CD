package tests;

import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.BaseTest;
import utils.DriverManager;

public class LoginTests extends BaseTest {

    // Test 1: Verify successful login with valid credentials
    @Test
    public void testSuccessfulLoginWithValidCredentials() {
        AndroidDriver driver = DriverManager.getDriver();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("bob@example.com", "10203040");

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsScreenDisplayed(),
                "Products screen should be displayed after successful login");
    }

    // Test 2: Verify error message appears with wrong password
    @Test
    public void testLoginWithInvalidPasswordShowsError() {
        AndroidDriver driver = DriverManager.getDriver();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("bob@example.com", "wrongpassword");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid password");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
                "Error message text should mention username and password mismatch");
    }

    // Test 3: Verify error message appears with empty username
    @Test
    public void testLoginWithEmptyUsernameShowsError() {
        AndroidDriver driver = DriverManager.getDriver();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("", "10203040");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed when username is empty");
    }

    // Test 4: Verify error message appears with empty password
    @Test
    public void testLoginWithEmptyPasswordShowsError() {
        AndroidDriver driver = DriverManager.getDriver();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("bob@example.com", "");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed when password is empty");
    }

    // Test 5: Verify login screen is displayed on app launch
    @Test
    public void testLoginScreenIsDisplayedOnAppLaunch() {
        AndroidDriver driver = DriverManager.getDriver();
        LoginPage loginPage = new LoginPage(driver);

        Assert.assertTrue(loginPage.isLoginScreenDisplayed(),
                "Login screen should be displayed when app launches");
    }

    //5 Test Cases Written Successfully for Login Functionality
}
