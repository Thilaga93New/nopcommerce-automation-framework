package reporting;

import org.thilaga.automation.config.ConfigReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class ReportingEnvironmentWriter {

    private ReportingEnvironmentWriter() {
    }

    public static void write() {

        Path resultsDirectory =
                Path.of("target", "allure-results");

        try {
            Files.createDirectories(resultsDirectory);

            Properties properties = new Properties();

            properties.setProperty(
                    "Base URL",
                    ConfigReader.get("base.url")
            );

            properties.setProperty(
                    "Browser",
                    ConfigReader.get("browser")
            );

            properties.setProperty(
                    "Headless",
                    ConfigReader.get("headless")
            );

            properties.setProperty(
                    "OS",
                    System.getProperty("os.name")
            );

            properties.setProperty(
                    "Java Version",
                    System.getProperty("java.version")
            );

            try (var output =
                         Files.newOutputStream(
                                 resultsDirectory.resolve(
                                         "environment.properties"
                                 )
                         )) {

                properties.store(
                        output,
                        "Automation Test Environment"
                );
            }

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to create Allure environment information",
                    e
            );
        }
    }
}