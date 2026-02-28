package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;
import java.time.Duration;

public class DriverManager {

    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void initDriver() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("R5CX21KD1CB");
        options.setPlatformVersion("14");
        options.setAppPackage("com.saucelabs.mydemoapp.rn");
        options.setAppActivity(".MainActivity");
        options.setApp(System.getProperty("user.dir") + "/src/test/resources/MyDemoAppRN.apk");
        options.setNoReset(false);
        options.setNewCommandTimeout(Duration.ofSeconds(60));
        options.setAutoGrantPermissions(true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
