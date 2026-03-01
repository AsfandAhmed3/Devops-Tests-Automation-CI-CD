# Copilot Instructions – Devops Tests Automation CI-CD

## Project Overview
Android mobile test automation for the **Sauce Labs My Demo App (React Native)** using **Appium + TestNG + Selenium (Page Object Model)**. Tests run on a physical Android device via a local Appium server.

## Architecture

```
src/test/java/
  pages/        ← Page Object classes (one per app screen)
  tests/        ← TestNG test classes (extend BaseTest)
  utils/
    BaseTest.java      ← @BeforeMethod / @AfterMethod lifecycle
    DriverManager.java ← AndroidDriver singleton init/quit
src/test/resources/
  MyDemoAppRN.apk      ← APK must be placed here manually
```

**Data flow:** `BaseTest` → `DriverManager.initDriver()` → `AndroidDriver` → passed into Page constructors → test methods call page actions → `DriverManager.quitDriver()`.

## Critical Setup Requirements (non-obvious)

1. **Appium server must be running locally** on `http://127.0.0.1:4723` before executing any test.
   ```
   appium
   ```
2. **Physical Android device** with serial `R5CX21KD1CB` must be connected via USB with USB debugging enabled. Screen must be unlocked and idle (dismiss any playing video/overlay).
3. **APK must exist** at `src/test/resources/MyDemoAppRN.apk`. Appium installs it automatically – do NOT pre-install the app manually.
4. `noReset=false` in `DriverManager` means the app is **fully reinstalled on every test run**. Change to `true` to keep app state between runs.

## Running Tests

```bash
# Run all tests via Maven (from project root)
mvn clean test

# Run a single test class
mvn clean test -Dtest=LoginTests

# Skip compilation, run only tests
mvn test -DskipCompile
```

## Page Object Conventions

- All pages extend nothing – they receive `AndroidDriver` in the constructor and call `PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this)`.
- Elements use `@AndroidFindBy(accessibilityId = "test-<ID>")` for stable locators. XPath is used only for child text elements inside a container (e.g., product name/price inside `test-Description`).
- Visibility checks always wrap in `try/catch` returning `false` — never throw `NoSuchElementException` from page methods.

```java
// Pattern for all isXxxDisplayed() methods
public boolean isLoginScreenDisplayed() {
    try { return loginButton.isDisplayed(); }
    catch (Exception e) { return false; }
}
```

## Test Credentials
- Valid user: `bob@example.com` / `10203040`
- Any other username/password combination triggers the "Username and password do not match" error.

## Target App Details
- Package: `com.saucelabs.mydemoapp.rn`
- Activity: `.MainActivity`
- Android version: 14
- Device serial: `R5CX21KD1CB`

## Adding New Tests
1. Add a new Page class in `pages/` following the `AppiumFieldDecorator` + `@AndroidFindBy` pattern.
2. Add a new test class in `tests/` that `extends BaseTest` — `setUp()`/`tearDown()` are inherited.
3. Register the class in `testng.xml` under a `<test>` block.

## Dependencies (pom.xml)
- `io.appium:java-client:8.6.0`
- `org.seleniumhq.selenium:selenium-java:4.15.0`
- `org.testng:testng:7.8.0`
- Java 17, Maven Surefire 3.2.2 (reads `testng.xml`)
