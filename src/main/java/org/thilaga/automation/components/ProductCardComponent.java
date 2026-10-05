package org.thilaga.automation.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.thilaga.automation.actions.ScopedActions;
import org.thilaga.automation.config.ConfigReader;
import org.thilaga.automation.pages.ProductPage;

public class ProductCardComponent extends BaseComponent {

    private final ScopedActions scopedActions;

    private final By productName =
            By.cssSelector(".product-title a");

    private final By price =
            By.cssSelector(".actual-price");

    private final By addToCartButton =
            By.cssSelector(".product-box-add-to-cart-button");


    public ProductCardComponent(WebDriver driver, By rootLocator) {
        super(driver);

        int timeout = Integer.parseInt(
                ConfigReader.get("timeout")
        );

        this.scopedActions =
                new ScopedActions(driver, rootLocator, timeout);
    }


    public String getProductName() {
        return scopedActions.getText(productName);
    }


    public String getPrice() {
        return scopedActions.getText(price);
    }


    public ProductPage addToCart() {
        scopedActions.click(addToCartButton);

        return new ProductPage(driver);
    }
}