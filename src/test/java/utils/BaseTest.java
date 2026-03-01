package utils;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Map;

public class BaseTest {

    private static final String APP_PACKAGE   = "com.saucelabs.mydemoapp.rn";
    private static final String MENU_BTN_ID   = "open menu";
    private static final String LOGIN_ITEM_ID = "menu item log in";

    @BeforeClass
    public void setUp() throws Exception {
        DriverManager.initDriver();
    }

    @BeforeMethod
    public void navigateToLogin() {
        // Clear app data so no saved login state, then relaunch (lands on Products)
        DriverManager.getDriver().executeScript("mobile: clearApp", Map.of("appId", APP_PACKAGE));
        DriverManager.getDriver().activateApp(APP_PACKAGE);
        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}

        // Open sidebar → tap Log In
        DriverManager.getDriver().findElement(AppiumBy.accessibilityId(MENU_BTN_ID)).click();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        DriverManager.getDriver().findElement(AppiumBy.accessibilityId(LOGIN_ITEM_ID)).click();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
