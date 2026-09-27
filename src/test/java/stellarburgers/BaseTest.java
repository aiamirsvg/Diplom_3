package stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.PageLoadStrategy;

import java.nio.file.Paths;
import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUpBrowser() {
        String browser = System.getProperty("browser", "chrome");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");

        if ("yandex".equalsIgnoreCase(browser)) {
            String yandexPath =
                    "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe";

            WebDriverManager.chromedriver()
                    .browserVersion("150")
                    .setup();

            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            options.setBinary(yandexPath);
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--remote-allow-origins=*");
        } else {
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            WebDriverManager.chromedriver().setup();

        }

        driver = new ChromeDriver(options);
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(3));
    }

    @After
    public void tearDownBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}