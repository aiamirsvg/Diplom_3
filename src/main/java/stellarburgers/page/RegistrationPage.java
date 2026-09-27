package stellarburgers.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.time.Duration;

public class RegistrationPage {

    public static final String URL =
            "https://stellarburgers.education-services.ru/register";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameInput =
            By.xpath("//label[text()='Имя']/following-sibling::input");

    private final By emailInput =
            By.xpath("//label[text()='Email']/following-sibling::input");

    private final By passwordInput =
            By.xpath("//label[text()='Пароль']/following-sibling::input");

    private final By registerButton =
            By.xpath("//button[text()='Зарегистрироваться']");

    private final By loginLink =
            By.xpath("//a[text()='Войти']");

    private final By incorrectPasswordMessage =
            By.xpath("//p[text()='Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void open() {
        driver.get(URL);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(registerButton)
        );
    }

    public void register(String name, String email, String password) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(registerButton).click();
    }

    public void clickLoginLink() {
        WebElement link = wait.until(
                ExpectedConditions.presenceOfElementLocated(loginLink)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", link);
    }

    public boolean isIncorrectPasswordMessageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        incorrectPasswordMessage
                )
        ).isDisplayed();
    }
}