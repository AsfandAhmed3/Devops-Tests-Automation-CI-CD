# DevOps Tests Automation CI-CD

![Java](https://img.shields.io/badge/Java-17-007396?style=flat&logo=java)
![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=flat&logo=apache-maven)
![Appium](https://img.shields.io/badge/Appium-8.6.0-662D91?style=flat&logo=appium)
![TestNG](https://img.shields.io/badge/TestNG-7.8.0-FF6C37?style=flat)
![Selenium](https://img.shields.io/badge/Selenium-4.15.0-43B02A?style=flat&logo=selenium)

A comprehensive mobile test automation framework for Android applications using Appium, TestNG, and Selenium with Page Object Model (POM) design pattern. This project is designed for CI/CD integration and demonstrates best practices in mobile test automation.

## 📋 Table of Contents

- [Overview](#overview)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Scenarios](#test-scenarios)
- [Framework Architecture](#framework-architecture)
- [CI/CD Integration](#cicd-integration)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

This project provides an automated testing solution for the **My Demo App RN** (React Native) Android application. It implements a robust test automation framework following industry best practices and design patterns, making it suitable for enterprise-level mobile test automation.

### Key Features

- ✨ **Page Object Model (POM)** design pattern for maintainable code
- 🔧 **Maven** for dependency management and build lifecycle
- 🧪 **TestNG** for test orchestration and reporting
- 📱 **Appium** for mobile automation on Android devices
- 🚀 **CI/CD ready** with Maven Surefire plugin
- 📊 **Comprehensive test coverage** for critical user flows

## 🛠 Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming language |
| Maven | 3.x | Build automation and dependency management |
| Appium | 8.6.0 | Mobile automation framework |
| TestNG | 7.8.0 | Testing framework |
| Selenium | 4.15.0 | WebDriver API for browser/app automation |
| UiAutomator2 | - | Android automation driver |

## 📁 Project Structure

```
Devops-Tests-Automation-CI-CD/
│
├── src/
│   ├── main/
│   │   └── java/                    # Main application code (if any)
│   │
│   └── test/
│       ├── java/
│       │   ├── pages/               # Page Object Model classes
│       │   │   ├── CartPage.java
│       │   │   ├── CheckoutPage.java
│       │   │   ├── LoginPage.java
│       │   │   ├── ProductDetailPage.java
│       │   │   └── ProductsPage.java
│       │   │
│       │   ├── tests/               # Test classes
│       │   │   └── LoginTests.java
│       │   │
│       │   └── utils/               # Utility classes
│       │       ├── BaseTest.java    # Base test setup/teardown
│       │       └── DriverManager.java # WebDriver management
│       │
│       └── resources/
│           └── MyDemoAppRN.apk      # Android application under test
│
├── pom.xml                          # Maven configuration
├── testng.xml                       # TestNG suite configuration
├── .gitignore                       # Git ignore rules
└── README.md                        # Project documentation

```

## ✅ Prerequisites

Before running the tests, ensure you have the following installed:

1. **Java Development Kit (JDK) 17 or higher**
   ```bash
   java -version
   ```

2. **Apache Maven 3.8+**
   ```bash
   mvn -version
   ```

3. **Node.js and npm** (for Appium)
   ```bash
   node -version
   npm -version
   ```

4. **Appium Server**
   ```bash
   npm install -g appium
   appium -v
   ```

5. **Appium UiAutomator2 Driver**
   ```bash
   appium driver install uiautomator2
   ```

6. **Android SDK** with required tools:
   - Android SDK Platform-Tools
   - Android SDK Build-Tools
   - Set `ANDROID_HOME` environment variable

7. **Android Device or Emulator**
   - Real device connected via USB with USB debugging enabled
   - OR Android emulator running

## 🚀 Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Devops-Tests-Automation-CI-CD
   ```

2. **Install Maven dependencies**
   ```bash
   mvn clean install -DskipTests
   ```

3. **Place the APK file**
   - Ensure `MyDemoAppRN.apk` is placed in `src/test/resources/` directory
   - Or update the APK path in `DriverManager.java`

4. **Verify Android device connection**
   ```bash
   adb devices
   ```

## ⚙️ Configuration

### Update Device Configuration

Edit `src/test/java/utils/DriverManager.java` to match your device:

```java
options.setDeviceName("YOUR_DEVICE_ID");  // Get from 'adb devices'
options.setPlatformVersion("YOUR_ANDROID_VERSION");
```

### Appium Server

Start the Appium server before running tests:
```bash
appium
```

Or specify a custom host/port:
```bash
appium --address 127.0.0.1 --port 4723
```

### TestNG Configuration

Modify `testng.xml` to customize test execution:
- Enable/disable parallel execution
- Add/remove test classes
- Configure test parameters

## 🧪 Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific test suite
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Run specific test class
```bash
mvn clean test -Dtest=LoginTests
```

### Run specific test method
```bash
mvn clean test -Dtest=LoginTests#testSuccessfulLoginWithValidCredentials
```

### Generate reports
TestNG generates HTML reports in `test-output/` directory after test execution.

## 📝 Test Scenarios

### Login Tests (LoginTests.java)

| Test Case | Description | Status |
|-----------|-------------|--------|
| `testSuccessfulLoginWithValidCredentials` | Verifies successful login with valid username and password | ✅ |
| `testLoginWithInvalidPasswordShowsError` | Verifies error message for incorrect password | ✅ |
| `testLoginWithEmptyUsernameShowsError` | Verifies error message when username field is empty | ✅ |
| `testLoginWithEmptyPasswordShowsError` | Verifies error message when password field is empty | ✅ |
| `testLoginScreenIsDisplayedOnAppLaunch` | Verifies login screen displays on app launch | ✅ |

**Test Credentials:**
- Valid Username: `bob@example.com`
- Valid Password: `10203040`

## 🏗 Framework Architecture

### Page Object Model (POM)

The framework implements the Page Object Model design pattern for better maintainability:

```
┌─────────────────┐
│   Test Layer    │  LoginTests.java
│   (tests/)      │  Uses page objects to perform actions
└────────┬────────┘
         │
┌────────▼────────┐
│  Page Objects   │  LoginPage.java, ProductsPage.java
│   (pages/)      │  Encapsulates page elements and actions
└────────┬────────┘
         │
┌────────▼────────┐
│  Utilities      │  DriverManager.java, BaseTest.java
│   (utils/)      │  Driver initialization and base test setup
└─────────────────┘
```

### Key Components

**1. DriverManager.java**
- Manages Appium AndroidDriver lifecycle
- Configures device capabilities
- Handles driver initialization and teardown

**2. BaseTest.java**
- Provides `@BeforeMethod` for test setup
- Provides `@AfterMethod` for test cleanup
- Base class for all test classes

**3. Page Objects**
- Encapsulate page elements using `@AndroidFindBy` annotations
- Provide methods for page interactions
- Use AppiumFieldDecorator for element initialization

**4. Test Classes**
- Extend `BaseTest` for setup/teardown
- Use TestNG annotations (`@Test`)
- Implement assertions for verification

## 🔄 CI/CD Integration

This framework is designed for seamless CI/CD integration:

### Maven Surefire Plugin

Configured in `pom.xml` for automated test execution:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.2</version>
    <configuration>
        <suiteXmlFiles>
            <suiteXmlFile>testng.xml</suiteXmlFile>
        </suiteXmlFiles>
    </configuration>
</plugin>
```

### CI/CD Pipeline Example

```yaml
# Example for GitHub Actions / Azure DevOps / Jenkins
steps:
  - name: Checkout code
  - name: Set up JDK 17
  - name: Set up Android SDK
  - name: Start Appium Server
  - name: Start Android Emulator
  - name: Run tests
    command: mvn clean test
  - name: Publish test reports
```

### Best Practices for CI/CD

- Use Docker containers for consistent test environment
- Configure Appium to run in headless mode
- Use cloud device farms (BrowserStack, Sauce Labs, AWS Device Farm)
- Archive test reports and artifacts
- Implement retry mechanism for flaky tests

## 🤝 Contributing

Contributions are welcome! Please follow these guidelines:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Open a Pull Request

### Coding Standards

- Follow Java naming conventions
- Add JavaDoc comments for public methods
- Write meaningful test names
- Maintain Page Object Model structure
- Update documentation for new features

## 📧 Contact

For questions or support, please open an issue in the repository.

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

**Happy Testing! 🚀**
