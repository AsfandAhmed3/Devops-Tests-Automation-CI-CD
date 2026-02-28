package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class CheckoutPage {

    private AndroidDriver driver;

    @AndroidFindBy(accessibilityId = "test-First Name")
    private WebElement firstNameField;

    @AndroidFindBy(accessibilityId = "test-Last Name")
    private WebElement lastNameField;

    @AndroidFindBy(accessibilityId = "test-Zip/Postal Code")
    private WebElement zipCodeField;

    @AndroidFindBy(accessibilityId = "test-CONTINUE")
    private WebElement continueButton;

    @AndroidFindBy(accessibilityId = "test-FINISH")
    private WebElement finishButton;

    @AndroidFindBy(accessibilityId = "test-CHECKOUT: COMPLETE!")
    private WebElement checkoutComplete;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Thank you for your order!\"]")
    private WebElement thankYouMessage;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    private WebElement errorMessage;

    public CheckoutPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void enterFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void enterZipCode(String zipCode) {
        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);
    }

    public void fillCheckoutForm(String firstName, String lastName, String zipCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipCode);
    }

    public void tapContinue() {
        continueButton.click();
    }

    public void tapFinish() {
        finishButton.click();
    }

    public boolean isOrderCompleted() {
        try {
            return thankYouMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
