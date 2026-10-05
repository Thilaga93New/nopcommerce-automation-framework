import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.*;
import org.thilaga.automation.components.NotificationComponent;
import org.thilaga.automation.pages.HomePage;
import org.thilaga.automation.pages.ProductPage;

public class CartTest extends BaseTest {

    private String productName;
    private String successMessage;

    @BeforeClass(alwaysRun = true)
    public void prepareCartTestData() {
        productName = "Apple MacBook Pro";
        successMessage = "shopping cart";

        System.out.println("CartTest @BeforeClass executed");
    }

    @Test(groups={"smoke","cart"})
    public void productCardNavigatesToProductPage() {

        HomePage homePage = new HomePage(getDriver());

        homePage.getProduct(productName)
                .addToCart();

        Assert.assertTrue(
                getDriver().getCurrentUrl()
                        .contains("apple-macbook-pro")
        );
    }

    @Test(groups={"smoke","cart"})
    public void addProductToCart() {

        HomePage homePage = new HomePage(getDriver());

        ProductPage productPage =
                homePage.getProduct(productName)
                        .addToCart();

        NotificationComponent notification =
                productPage.addToCart();

        Assert.assertTrue(
                notification.getMessage()
                        .contains(successMessage)
        );
    }

    @Test(dataProvider = "quantities",groups={"regression","cart"})
    @Parameters({"Quantity"})
    public void addProductWithDifferentQuantities(String quantity) {

        HomePage homePage = new HomePage(getDriver());

        ProductPage productPage =
                homePage.getProduct(productName)
                        .addToCart();

        Allure.step(
                "Set product quantity to " + quantity,
                () -> productPage.setQuantity(quantity)
        );

        NotificationComponent notification =
                productPage.addToCart();

        Assert.assertTrue(
                notification.getMessage()
                        .contains("shopping cart")
        );
    }
    @BeforeGroups("cart")
    public void beforeCartGroup() {
        System.out.println(">>> CART GROUP STARTED");
    }

    @AfterGroups("cart")
    public void afterCartGroup() {
        System.out.println(">>> CART GROUP COMPLETED");
    }

    @DataProvider(name = "quantities")
    public Object[][] quantities() {

        return new Object[][]{
                {"2"},
                {"3"},
                {"4"}
        };
    }

    @AfterClass(alwaysRun = true)
    public void afterCartTests() {
        System.out.println("CartTest @AfterClass executed");
    }
}