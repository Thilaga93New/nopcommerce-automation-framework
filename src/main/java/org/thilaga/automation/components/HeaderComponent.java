package org.thilaga.automation.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.thilaga.automation.pages.LoginPage;

public class HeaderComponent extends BaseComponent {

    private final By registerLink = By.cssSelector("a.ico-register");
    private final By loginLink = By.cssSelector("a.ico-login");
    private final By myAccountLink = By.cssSelector("a.ico-account");
    private final By logoutLink = By.cssSelector("a.ico-logout");
    private final By wishlistLink = By.cssSelector("a.ico-wishlist");
    private final By shoppingCartLink =
            By.cssSelector("#topcartlink a.ico-cart");

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLoginPage() {
        actions.click(loginLink);
        return new LoginPage(driver);
    }
}