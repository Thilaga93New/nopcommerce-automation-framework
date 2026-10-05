package org.thilaga.automation.components;

import org.openqa.selenium.WebDriver;
import org.thilaga.automation.ui.BaseUiObject;

public abstract class BaseComponent extends BaseUiObject {

    protected BaseComponent(WebDriver driver) {
        super(driver);
    }
}