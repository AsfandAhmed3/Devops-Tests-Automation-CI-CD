package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage {

    private static final String USERNAME_ID  = "Username input field";
    private static final String PASSWORD_ID  = "Password input field";
    private static final String LOGIN_BTN_ID = "Login button";
    private static final String SCREEN_ID    = "login screen";
    private static final String[] ERROR_CONTAINER_IDS = {
            "Username-error-message",
            "Password-error-message",
            "generic-error-message"
    };

    private final AndroidDriver driver;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        WebElement field = driver.findElement(AppiumBy.accessibilityId(USERNAME_ID));
        field.clear();
        if (username != null && !username.isEmpty()) field.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement field = driver.findElement(AppiumBy.accessibilityId(PASSWORD_ID));
        field.clear();
        if (password != null && !password.isEmpty()) field.sendKeys(password);
    }

    public void tapLogin() {
        try { driver.hideKeyboard(); } catch (Exception ignored) {}
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        driver.findElement(AppiumBy.accessibilityId(LOGIN_BTN_ID)).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        tapLogin();
    }

    public boolean isErrorMessageDisplayed() {
        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}
        for (String errorId : ERROR_CONTAINER_IDS) {
            try {
                List<WebElement> els = driver.findElements(AppiumBy.accessibilityId(errorId));
                if (!els.isEmpty() && els.get(0).isDisplayed()) return true;
            } catch (Exception ignored) {}
        }
        return false;
    }

    public String getErrorMessage() {
        for (String errorId : ERROR_CONTAINER_IDS) {
            try {
                WebElement container = driver.findElement(AppiumBy.accessibilityId(errorId));
                if (!container.isDisplayed()) continue;
                String text = container.getText();
                if (text != null && !text.trim().isEmpty()) return text.trim();
                List<WebElement> children = container.findElements(By.className("android.widget.TextView"));
                for (WebElement child : children) {
                    String t = child.getText();
                    if (t != null && !t.trim().isEmpty()) return t.trim();
                }
            } catch (Exception ignored) {}
        }
        return "";
    }

    public boolean isLoginScreenDisplayed() {
        try {
            return driver.findElement(AppiumBy.accessibilityId(SCREEN_ID)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
