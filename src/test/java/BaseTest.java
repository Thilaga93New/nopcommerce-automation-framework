import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.thilaga.automation.config.ConfigReader;
import org.thilaga.automation.driver.DriverManager;
import reporting.ReportingEnvironmentWriter;

public abstract class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {

        System.out.println("===== SUITE STARTED =====");

        // Realistic suite-level validation
        ConfigReader.get("base.url");
        ConfigReader.get("browser");
        ConfigReader.get("timeout");
        ConfigReader.get("headless");
        ReportingEnvironmentWriter.write();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        DriverManager.initializeDriver(
                ConfigReader.get("browser")
        );

        getDriver().get(
                ConfigReader.get("base.url")
        );
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @BeforeTest(alwaysRun = true)
    public void beforeXmlTest() {
        System.out.println(">>> @BeforeTest executed");
    }

    @AfterTest(alwaysRun = true)
    public void afterXmlTest() {
        System.out.println(">>> @AfterTest executed");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        System.out.println("===== SUITE COMPLETED =====");
    }
}