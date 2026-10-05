package org.thilaga.automation.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NotificationComponent extends BaseComponent {

    private final By notification =
            By.cssSelector(".bar-notification.success");

    public NotificationComponent(WebDriver driver) {
        super(driver);
    }

    public String getMessage() {
        return actions.getText(notification);
    }
}