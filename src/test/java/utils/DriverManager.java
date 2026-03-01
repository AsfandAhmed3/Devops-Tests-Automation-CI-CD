package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;
import java.time.Duration;

public class DriverManager {

    private static AndroidDriver driver;
    private static final int MAX_RETRIES = 3;
    private static final int RETRY_DELAY_MS = 15000;

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void initDriver() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("R5CX21KD1CB");
        options.setPlatformVersion("14");
        options.setAppPackage("com.saucelabs.mydemoapp.rn");
        options.setAppActivity(".MainActivity");
        // Do NOT set options.setApp(...) — avoids apksigner check (Build Tools not required).
        // Install the APK manually once: adb install -r src/test/resources/MyDemoAppRN.apk
        options.setNoReset(true);
        options.setNewCommandTimeout(Duration.ofSeconds(60));
        options.setAutoGrantPermissions(true);

        Exception lastException = null;
        for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
            try {
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return; // success
            } catch (Exception e) {
                lastException = e;
                System.out.println("[DriverManager] Session attempt " + attempt + " failed: " + e.getMessage());
                if (attempt < MAX_RETRIES) {
                    System.out.println("[DriverManager] Retrying in " + (RETRY_DELAY_MS / 1000) + "s...");
                    Thread.sleep(RETRY_DELAY_MS);
                }
            }
        }
        throw lastException;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
