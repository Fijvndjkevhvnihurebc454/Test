package framework.base;

import framework.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static String envPropertiesPath = "src/test/resources/env.properties";
    private static String url;
    private static String browser;
    private static final Logger LOG = LoggerFactory.getLogger(Configuration.class);

    public static void getResourcesFromPropertyFile() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(envPropertiesPath);
            properties.load(inputStream);
            url = properties.getProperty("url");
            browser = properties.getProperty("browser");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String getUrl() {
        LOG.info(String.format("Getting current URL"));
        return url;
    }

    public static String getBrowser() {
        LOG.info(String.format("Getting current browser"));
        return browser;
    }
}
