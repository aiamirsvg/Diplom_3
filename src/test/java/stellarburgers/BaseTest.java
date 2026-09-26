package stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;
import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUpBrowser() {
        String browserName =
                System.getProperty("browser", "chrome");

        ChromeOptions options = new ChromeOptions();

        if ("yandex".equalsIgnoreCase(browserName)) {
            String yandexPath = Paths.get(
                    System.getenv("LOCALAPPDATA"),
                    "Yandex",
                    "YandexBrowser",
                    "Application",
                    "browser.exe"
            ).toString();

            WebDriverManager.chromedriver().setup();
            options.setBinary(yandexPath);
        } else {
            WebDriverManager.chromedriver().setup();
        }

        driver = new ChromeDriver(options);
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
    }

    @After
    public void tearDownBrowser() {
        if (driver != null) {            driver.quit();
        }
    }
}