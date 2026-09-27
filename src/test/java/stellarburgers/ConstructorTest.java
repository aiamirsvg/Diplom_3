package stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellarburgers.page.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("Проверяем переключение с раздела соусов на булки")
    public void switchToBunsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        assertTrue(mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("Проверяем переключение на раздел соусов")
    public void switchToSaucesSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        mainPage.clickSaucesTab();

        assertTrue(mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("Проверяем переключение на раздел начинок")
    public void switchToFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        mainPage.clickFillingsTab();

        assertTrue(mainPage.isFillingsTabActive());
    }
}