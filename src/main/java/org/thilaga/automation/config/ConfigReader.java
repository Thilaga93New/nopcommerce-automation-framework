package org.thilaga.automation.config;

import org.thilaga.automation.exceptions.ConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input =
                     ConfigReader.class
                             .getClassLoader()
                             .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new ConfigurationException(
                        "config.properties file not found"
                );
            }

            properties.load(input);

        } catch (IOException e) {
            throw new ConfigurationException(
                    "Unable to load config.properties",
                    e
            );
        }
    }



    public static String get(String key) {

        String value = System.getProperty(key);

        if (value == null || value.isBlank()) {
            value = properties.getProperty(key);
        }

        if (value == null || value.isBlank()) {
            throw new ConfigurationException(
                    "Required configuration is missing: " + key
            );
        }

        return value;
    }

}