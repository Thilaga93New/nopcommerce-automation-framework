package org.thilaga.automation.pages;

import org.openqa.selenium.WebDriver;
import org.thilaga.automation.ui.BaseUiObject;

public abstract class BasePage extends BaseUiObject {

    protected BasePage(WebDriver driver) {
        super(driver);
    }
}