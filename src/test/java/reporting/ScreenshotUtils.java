package reporting;

import io.qameta.allure.Allure;
import io.qameta.allure.AttachmentOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public final class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    public static void attachScreenshot(WebDriver driver) {

        if (driver == null) {
            return;
        }

        byte[] screenshot =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);

        Allure.attachment(
                "Failure Screenshot",
                "image/png",
                new ByteArrayInputStream(screenshot),
                AttachmentOptions.withFileExtension(".png")
        );
    }
}