import org.testng.Assert;
import org.testng.annotations.Test;
import org.thilaga.automation.components.ProductCardComponent;
import org.thilaga.automation.config.ConfigReader;
import org.thilaga.automation.pages.HomePage;

public class HomePageTest extends BaseTest {

    @Test(groups="home",description = "loading home page")
    public void homePageLoads() {

        Assert.assertTrue(
                getDriver().getCurrentUrl()
                        .startsWith(ConfigReader.get("base.url"))
        );
    }

    @Test(groups="home")
    public void productNameIsDisplayedCorrectly() {

        HomePage homePage = new HomePage(getDriver());

        ProductCardComponent product =
                homePage.getProduct("Apple MacBook Pro");

        Assert.assertEquals(
                product.getProductName(),
                "Apple MacBook Pro"
        );
    }

    @Test(groups="home")
    public void productPriceIsDisplayed() {

        HomePage homePage = new HomePage(getDriver());

        String price =
                homePage.getProduct("Apple MacBook Pro")
                        .getPrice();

        Assert.assertFalse(price.isBlank());
    }


    @Test(groups="home")
    public void loginLinkNavigatesToLoginPage() {

        HomePage homePage = new HomePage(getDriver());

        homePage.header()
                .goToLoginPage();

        Assert.assertTrue(
                getDriver().getCurrentUrl().contains("/login")
        );
    }

    @Test(groups="home",description = "loading home page with various layouts",timeOut = 3000)
    public void timeoutDemo() throws InterruptedException {
        Thread.sleep(5000);
    }
}