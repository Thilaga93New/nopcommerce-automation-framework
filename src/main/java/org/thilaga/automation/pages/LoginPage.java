package org.thilaga.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By emailField = By.id("Email");
    private final By passwordField = By.id("Password");
    private final By loginButton =
            By.xpath("//button[text()='Log in']");
    private final By forgotPassword =
            By.xpath("//a[text()='Forgot password?']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        actions.type(emailField, email);
        actions.type(passwordField, password);
        actions.click(loginButton);
    }
}