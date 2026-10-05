import org.testng.Assert;
import org.testng.annotations.Test;
import org.thilaga.automation.config.ConfigReader;

public class ConfigurationTest {

    @Test
    public void baseUrlIsConfigured() {

        Assert.assertFalse(
                ConfigReader.get("base.url").isBlank()
        );
    }

    @Test
    public void browserIsConfigured() {

        Assert.assertFalse(
                ConfigReader.get("browser").isBlank()
        );
    }

    @Test
    public void timeoutIsPositive() {

        int timeout =
                Integer.parseInt(
                        ConfigReader.get("timeout")
                );

        Assert.assertTrue(timeout > 0);
    }

    @Test
    public void headlessValueIsValid() {

        String headless =
                ConfigReader.get("headless");

        Assert.assertTrue(
                headless.equalsIgnoreCase("true")
                        || headless.equalsIgnoreCase("false")
        );
    }
}