package stellarburgers.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import io.qameta.allure.Step;

import java.time.Duration;

public class MainPage {

    public static final String URL =
            "https://stellarburgers.education-services.ru/";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginButton =
            By.xpath("//button[text()='Войти в аккаунт']");

    private final By accountLink =
            By.xpath("//a[@href='/account']");

    private final By constructorLink =
            By.xpath("//a[@href='/' and .//p[text()='Конструктор']]");

    private final By orderButton =
            By.xpath("//button[text()='Оформить заказ']");

    private final By bunsTab =
            By.xpath(
                    "//div[contains(@class,'tab_tab') and contains(.,'Булки')]"
            );

    private final By saucesTab =
            By.xpath(
                    "//div[contains(@class,'tab_tab') and contains(.,'Соусы')]"
            );

    private final By fillingsTab =
            By.xpath(
                    "//div[contains(@class,'tab_tab') and contains(.,'Начинки')]"
            );

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get(URL);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(bunsTab)
        );
    }

    @Step("Нажать кнопку «Войти в аккаунт»")
    public void clickLoginButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(loginButton)
        ).click();
    }

    @Step("Нажать ссылку «Личный кабинет»")
    public void clickAccountLink() {
        driver.findElement(accountLink).click();
    }

    @Step("Нажать ссылку «Конструктор»")
    public void clickConstructorLink() {
        driver.findElement(constructorLink).click();
    }

    @Step("Проверить отображение кнопки оформления заказа")
    public boolean isOrderButtonDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(orderButton)
        ).isDisplayed();
    }

    @Step("Перейти к разделу «Булки»")
    public void clickBunsTab() {
        clickTab(bunsTab);
    }

    @Step("Перейти к разделу «Соусы»")
    public void clickSaucesTab() {
        clickTab(saucesTab);
    }

    @Step("Перейти к разделу «Начинки»")
    public void clickFillingsTab() {
        clickTab(fillingsTab);
    }

    @Step("Проверить активность раздела «Булки»")
    public boolean isBunsTabActive() {
        return isTabActive(bunsTab);
    }

    @Step("Проверить активность раздела «Соусы»")
    public boolean isSaucesTabActive() {
        return isTabActive(saucesTab);
    }

    @Step("Проверить активность раздела «Начинки»")
    public boolean isFillingsTabActive() {
        return isTabActive(fillingsTab);
    }

    private boolean isTabActive(By locator) {
        return wait.until(
                ExpectedConditions.attributeContains(
                        locator,
                        "class",
                        "tab_type_current"
                )
        );
    }
    private void clickTab(By locator) {
        WebElement tab = wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", tab);
    }
}