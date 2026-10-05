package org.thilaga.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenNopCommerce {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.get("https://demo.nopcommerce.com/");
        System.out.println(org.thilaga.automation.config.ConfigReader.get("base.url"));
        System.out.println(org.thilaga.automation.config.ConfigReader.get("browser"));

        driver.quit();
    }
}