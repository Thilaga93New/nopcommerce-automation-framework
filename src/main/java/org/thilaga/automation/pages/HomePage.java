package org.thilaga.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.thilaga.automation.components.HeaderComponent;
import org.thilaga.automation.components.ProductCardComponent;

public class HomePage extends BasePage {

    private final HeaderComponent header;


    public HomePage(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver);
    }

    public HeaderComponent header() {
        return header;
    }

    public ProductCardComponent getProduct(String productName) {
        By productRoot = By.xpath(
                "//article[contains(@class,'product-item')]" +
                        "[.//h2[contains(@class,'product-title')]/a[normalize-space()='"
                        + productName + "']]"
        );

        return new ProductCardComponent(driver, productRoot);
    }
}
