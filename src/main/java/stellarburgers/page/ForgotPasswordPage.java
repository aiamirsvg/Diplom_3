package stellarburgers.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

import java.time.Duration;

public class ForgotPasswordPage {

    public static final String URL =
            "https://stellarburgers.education-services.ru/forgot-password";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginLink =
            By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Step("Открыть страницу восстановления пароля")
    public void open() {
        driver.get(URL);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginLink)
        );
    }

    @Step("Нажать ссылку входа на странице восстановления пароля")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}