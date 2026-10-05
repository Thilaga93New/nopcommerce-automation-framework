package org.thilaga.automation.ui;

import org.openqa.selenium.WebDriver;
import org.thilaga.automation.actions.ElementActions;
import org.thilaga.automation.config.ConfigReader;

public abstract class BaseUiObject {

    protected WebDriver driver;
    protected ElementActions actions;

    protected BaseUiObject(WebDriver driver) {
        this.driver = driver;

        int timeout = Integer.parseInt(
                ConfigReader.get("timeout")
        );

        this.actions = new ElementActions(driver, timeout);
    }
}