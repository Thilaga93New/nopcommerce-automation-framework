package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.thilaga.automation.driver.DriverManager;
import reporting.AllureReportManager;
import reporting.ScreenshotUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {

        System.out.println(
                "\n===== XML TEST STARTED: "
                        + context.getName()
                        + " ====="
        );
    }

    @Override
    public void onTestStart(ITestResult result) {

        AllureReportManager.addTestMetadata(result);

        System.out.println(
                "\nSTARTED: "
                        + result.getMethod().getMethodName()
        );

        System.out.println(
                "Class: "
                        + result.getTestClass().getName()
        );

        System.out.println(
                "Groups: "
                        + Arrays.toString(
                        result.getMethod().getGroups()
                )
        );

        System.out.println(
                "Parameters: "
                        + Arrays.toString(
                        result.getParameters()
                )
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        long duration =
                result.getEndMillis()
                        - result.getStartMillis();

        System.out.println(
                "PASSED: "
                        + result.getMethod().getMethodName()
                        + " | Duration: "
                        + duration
                        + " ms"
        );
    }

    @Override
    public void onTestFailure(ITestResult result) {

        long duration =
                result.getEndMillis()
                        - result.getStartMillis();

        System.out.println(
                "FAILED: "
                        + result.getMethod().getMethodName()
        );

        System.out.println(
                "Duration: "
                        + duration
                        + " ms"
        );

        System.out.println(
                "Exception: "
                        + result.getThrowable()
        );

        WebDriver driver = DriverManager.getDriver();

        ScreenshotUtils.attachScreenshot(driver);

        if (driver != null) {

            AllureReportManager.attachText(
                    "Current URL",
                    driver.getCurrentUrl()
            );
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        String skipDetails = buildSkipDetails(result);

        System.out.println(
                "SKIPPED: "
                        + result.getMethod().getMethodName()
        );

        System.out.println(
                "Skip details: "
                        + skipDetails
        );

        AllureReportManager.attachText(
                "Skip Details",
                skipDetails
        );
    }

    @Override
    public void onFinish(ITestContext context) {

        System.out.println(
                "Passed: "
                        + context.getPassedTests().size()
        );

        System.out.println(
                "Failed: "
                        + context.getFailedTests().size()
        );

        System.out.println(
                "Skipped: "
                        + context.getSkippedTests().size()
        );

        System.out.println(
                "===== XML TEST FINISHED: "
                        + context.getName()
                        + " =====\n"
        );
    }

    private String buildSkipDetails(ITestResult result) {

        List<ITestNGMethod> causes =
                result.getSkipCausedBy();

        if (!causes.isEmpty()) {

            String causeNames =
                    causes.stream()
                            .map(ITestNGMethod::getMethodName)
                            .distinct()
                            .collect(Collectors.joining(", "));

            boolean causedByTestMethod =
                    causes.stream()
                            .anyMatch(ITestNGMethod::isTest);

            if (causedByTestMethod) {

                return "Dependency Skip"
                        + System.lineSeparator()
                        + "Failed dependency: "
                        + causeNames;
            }

            return "Configuration-caused Skip"
                    + System.lineSeparator()
                    + "Failed configuration: "
                    + causeNames;
        }

        Throwable throwable =
                result.getThrowable();

        if (throwable instanceof SkipException) {

            return "Explicit Skip"
                    + System.lineSeparator()
                    + "Reason: "
                    + throwable.getMessage();
        }

        return "Skipped by TestNG - no specific cause was exposed";
    }
}