package browser;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


import static browser.Browser.YANDEX;
import static constants.Constants.*;


public class WebDriverFactory {
    public static WebDriver get() {
        WebDriver driver;

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(CHROME_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String browserName = properties.getProperty("browser");
        if (browserName == null) {
            browserName = YANDEX.getValue();
        }

        Browser browser = Browser.valueOf(browserName.toUpperCase());

        switch (browser) {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;

            case YANDEX:
                System.setProperty("webdriver.chrome.driver", YANDEX_PATH);
                ChromeOptions optionsYa = new ChromeOptions();
                optionsYa.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(optionsYa);
                break;

            default:
                throw new RuntimeException("Browser " + browserName + " not exist");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(SECONDS_TIMEOUT));
        driver.navigate().to(HOME_URL);
        return driver;
    }
}