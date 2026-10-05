package reporting;

import io.qameta.allure.Allure;
import org.testng.ITestResult;

public final class AllureReportManager {

    private AllureReportManager() {
    }

    public static void addTestMetadata(ITestResult result) {

        for (String group : result.getMethod().getGroups()) {
            Allure.label("tag", group);
        }
    }

    public static void attachText(
            String name,
            String value) {

        if (value == null || value.isBlank()) {
            return;
        }

        Allure.attachment(
                name,
                "text/plain",
                value
        );
    }
}