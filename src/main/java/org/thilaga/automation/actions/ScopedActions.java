package org.thilaga.automation.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.thilaga.automation.waits.WaitUtils;

public class ScopedActions {

    private final By rootLocator;
    private final WaitUtils waitUtils;

    public ScopedActions(WebDriver driver, By rootLocator, int timeout) {
        this.rootLocator = rootLocator;
        this.waitUtils = new WaitUtils(driver, timeout);
    }

    public void click(By childLocator) {

        waitUtils.waitUntil(webDriver -> {
            try {
                WebElement root =
                        webDriver.findElement(rootLocator);

                WebElement child =
                        root.findElement(childLocator);

                if (child.isDisplayed() && child.isEnabled()) {
                    child.click();
                    return true;
                }

                return false;

            } catch (NoSuchElementException |
                     StaleElementReferenceException e) {

                return false;
            }
        });
    }

    public void type(By childLocator, String text) {

        waitUtils.waitUntil(webDriver -> {
            try {
                WebElement root =
                        webDriver.findElement(rootLocator);

                WebElement child =
                        root.findElement(childLocator);

                if (child.isDisplayed() && child.isEnabled()) {
                    child.clear();
                    child.sendKeys(text);
                    return true;
                }

                return false;

            } catch (NoSuchElementException |
                     StaleElementReferenceException e) {

                return false;
            }
        });
    }

    public String getText(By childLocator) {

        return waitUtils.waitUntil(webDriver -> {
            try {
                WebElement root =
                        webDriver.findElement(rootLocator);

                WebElement child =
                        root.findElement(childLocator);

                if (child.isDisplayed()) {
                    return child.getText();
                }

                return null;

            } catch (NoSuchElementException |
                     StaleElementReferenceException e) {

                return null;
            }
        });
    }
}