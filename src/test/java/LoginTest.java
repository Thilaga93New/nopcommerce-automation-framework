import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.thilaga.automation.pages.HomePage;

import java.lang.reflect.Method;

public class LoginTest  extends BaseTest{

    @Test(groups = {"smoke","login"})
    @Severity(SeverityLevel.BLOCKER)
    public void navigateToLoginPage() {

        HomePage homePage = new HomePage(getDriver());

        homePage.header()
                .goToLoginPage();
    }
    @Test
    public void loginDependencyFailureDemo() {

        Assert.fail(
                "Intentional failure for reporting demo"
        );
    }

    @Test(dependsOnMethods = "loginDependencyFailureDemo")
    public void checkoutAfterLoginDemo() {

        System.out.println(
                "This should not execute"
        );
    }
    @BeforeMethod
    public void failConfigurationForDemo(Method method) {

        if (method.getName()
                .equals("configurationFailureDemo")) {

            Assert.fail(
                    "Intentional @BeforeMethod failure for reporting demo"
            );
        }

    }
    @Test
    public void configurationFailureDemo() {

        System.out.println(
                "This test body should never execute"
        );
    }
}
