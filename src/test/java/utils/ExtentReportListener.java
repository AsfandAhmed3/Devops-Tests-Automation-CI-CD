package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG listener that generates an HTML test report using ExtentReports.
 * Report is saved to: target/extent-report/index.html
 * Attach this listener in testng.xml via <listeners> tag.
 */
public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> testNode = new ThreadLocal<>();

    // ── Suite start: set up the reporter ──────────────────────────────────────
    @Override
    public void onStart(ITestContext context) {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir")
                    + "/target/extent-report/index.html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("Mobile Automation Report");
            spark.config().setReportName("Devops Tests Automation — Test Results");
            spark.config().setTimeStampFormat("dd MMM yyyy HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("App",         "My Demo App RN");
            extent.setSystemInfo("Platform",    "Android 14");
            extent.setSystemInfo("Device",      "Samsung (R5CX21KD1CB)");
            extent.setSystemInfo("Appium",      "8.6.0");
            extent.setSystemInfo("Java",        System.getProperty("java.version"));
            extent.setSystemInfo("Framework",   "Appium + TestNG + Page Object Model");
        }
    }

    // ── Each test start ────────────────────────────────────────────────────────
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(
                formatTestName(result.getMethod().getMethodName()),
                result.getMethod().getDescription()
        );
        testNode.set(test);
    }

    // ── Pass ───────────────────────────────────────────────────────────────────
    @Override
    public void onTestSuccess(ITestResult result) {
        testNode.get().log(Status.PASS, "Test passed ✔");
    }

    // ── Fail ───────────────────────────────────────────────────────────────────
    @Override
    public void onTestFailure(ITestResult result) {
        testNode.get().log(Status.FAIL, "Test failed ✘");
        testNode.get().log(Status.FAIL, result.getThrowable());
    }

    // ── Skip ───────────────────────────────────────────────────────────────────
    @Override
    public void onTestSkipped(ITestResult result) {
        testNode.get().log(Status.SKIP,
                "Test skipped — " + (result.getThrowable() != null
                        ? result.getThrowable().getMessage()
                        : "dependency failed"));
    }

    // ── Suite finish: flush the report to disk ─────────────────────────────────
    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
            System.out.println("\n[ExtentReport] Report saved → "
                    + System.getProperty("user.dir")
                    + "/target/extent-report/index.html\n");
        }
    }

    // ── Helpers ────────────────────────────────────────────────────────────────

    /** Converts camelCase method name to readable title. e.g. testLoginWithEmptyUsername → Test Login With Empty Username */
    private String formatTestName(String methodName) {
        // Remove "test" prefix, split on capitals
        String stripped = methodName.replaceFirst("^test", "");
        return stripped.replaceAll("([A-Z])", " $1").trim();
    }
}
