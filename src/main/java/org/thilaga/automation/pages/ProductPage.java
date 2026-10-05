package org.thilaga.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.thilaga.automation.components.NotificationComponent;


public class ProductPage extends BasePage {

    private final By productName =
            By.cssSelector(".product-name h1");

    private final By price =
            By.cssSelector(".product-price span[id^='price-value-']");

    private final By quantity =
            By.cssSelector(".add-to-cart-panel .qty-input");

    private final By addToCartButton =
            By.cssSelector(".add-to-cart-panel .add-to-cart-button");


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void setQuantity(String value) {
        actions.type(quantity, value);
    }

    public NotificationComponent addToCart() {
        actions.click(addToCartButton);

        return new NotificationComponent(driver);
    }
}