package stellarburgers.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

import java.time.Duration;

public class LoginPage {

    public static final String URL =
            "https://stellarburgers.education-services.ru/login";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput =
            By.xpath("//label[text()='Email']/following-sibling::input");

    private final By passwordInput =
            By.xpath("//label[text()='Пароль']/following-sibling::input");

    private final By loginButton =
            By.xpath("//button[text()='Войти']");

    private final By registerLink =
            By.xpath("//a[text()='Зарегистрироваться']");

    private final By forgotPasswordLink =
            By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Step("Открыть страницу входа")
    public void open() {
        driver.get(URL);
        waitForPage();
    }

    @Step("Дождаться загрузки страницы входа")
    public void waitForPage() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginButton)
        );
    }

    @Step("Войти с email: {email}")
    public void login(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Step("Нажать ссылку регистрации")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Нажать ссылку восстановления пароля")
    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }

    @Step("Проверить отображение кнопки входа")
    public boolean isLoginButtonDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginButton)
        ).isDisplayed();
    }
}