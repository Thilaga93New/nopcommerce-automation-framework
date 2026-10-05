package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.IConfigurationListener;
import org.testng.ITestResult;
import org.thilaga.automation.driver.DriverManager;
import reporting.AllureReportManager;
import reporting.ScreenshotUtils;

public class ConfigurationListener
        implements IConfigurationListener {

    @Override
    public void beforeConfiguration(ITestResult result) {

        System.out.println(
                "CONFIG STARTING: "
                        + result.getMethod().getMethodName()
        );
    }

    @Override
    public void onConfigurationSuccess(ITestResult result) {

        System.out.println(
                "CONFIG PASSED: "
                        + result.getMethod().getMethodName()
        );
    }

    @Override
    public void onConfigurationFailure(ITestResult result) {

        System.out.println(
                "CONFIG FAILED: "
                        + result.getMethod().getMethodName()
        );

        System.out.println(
                "Exception: "
                        + result.getThrowable()
        );

        WebDriver driver =
                DriverManager.getDriver();

        if (driver != null) {

            ScreenshotUtils.attachScreenshot(driver);

            AllureReportManager.attachText(
                    "Current URL",
                    driver.getCurrentUrl()
            );
        }
    }

    @Override
    public void onConfigurationSkip(ITestResult result) {

        System.out.println(
                "CONFIG SKIPPED: "
                        + result.getMethod().getMethodName()
        );
    }
}