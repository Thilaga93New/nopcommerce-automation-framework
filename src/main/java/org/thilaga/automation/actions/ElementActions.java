package org.thilaga.automation.actions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.thilaga.automation.waits.WaitUtils;

public class ElementActions {

    private final WaitUtils waitUtils;

    public ElementActions(WebDriver driver, int timeout) {
        this.waitUtils = new WaitUtils(driver, timeout);
    }

    public void click(By locator) {
        waitUtils.waitUntil(
                ExpectedConditions.elementToBeClickable(locator)
        ).click();
    }

    public void clickWithin(By rootLocator, By childLocator) {

       waitUtils.waitUntil(driver -> {

            try {
                WebElement root = driver.findElement(rootLocator);
                WebElement child = root.findElement(childLocator);

                if (child.isDisplayed() && child.isEnabled()) {
                    child.click();
                    return true;
                }

                return false;

            } catch (NoSuchElementException | StaleElementReferenceException e) {
                return false;
            }
        });
    }

    public void type(By locator, String text) {
        WebElement element = waitUtils.waitUntil(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return waitUtils.waitUntil(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).getText();
    }
}