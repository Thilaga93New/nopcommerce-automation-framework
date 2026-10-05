package org.thilaga.automation.waits;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver, int timeout) {
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(timeout)
        );
    }

    public <T> T waitUntil(ExpectedCondition<T> condition) {
        return wait.until(condition);
    }
}