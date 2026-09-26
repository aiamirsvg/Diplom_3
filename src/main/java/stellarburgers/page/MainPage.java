package stellarburgers.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
            By.xpath("//div[contains(@class,'tab_tab') and text()='Булки']");

    private final By saucesTab =
            By.xpath("//div[contains(@class,'tab_tab') and text()='Соусы']");

    private final By fillingsTab =
            By.xpath("//div[contains(@class,'tab_tab') and text()='Начинки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get(URL);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginButton)
        );
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickAccountLink() {
        driver.findElement(accountLink).click();
    }

    public void clickConstructorLink() {
        driver.findElement(constructorLink).click();
    }

    public boolean isOrderButtonDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(orderButton)
        ).isDisplayed();
    }

    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    public boolean isBunsTabActive() {
        return isTabActive(bunsTab);
    }

    public boolean isSaucesTabActive() {
        return isTabActive(saucesTab);
    }

    public boolean isFillingsTabActive() {
        return isTabActive(fillingsTab);
    }

    private boolean isTabActive(By locator) {
        WebElement tab = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        return tab.getAttribute("class")
                .contains("tab_type_current");
    }
}
