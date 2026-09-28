package stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.page.ForgotPasswordPage;
import stellarburgers.page.LoginPage;
import stellarburgers.page.MainPage;
import stellarburgers.page.RegistrationPage;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private String email;
    private final String password = "password123";

    @Before
    public void createUser() {
        email = "user-" + UUID.randomUUID() + "@example.com";
        UserApi.createUser(email, password, "Aiman");
    }

    @After
    public void deleteUser() {
        UserApi.deleteUser(email, password);
    }

    private void loginAndCheckSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPage();
        loginPage.login(email, password);

        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт»")
    @Description("Проверяем вход через кнопку на главной странице")
    public void loginFromMainLoginButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        loginAndCheckSuccess();
    }

    @Test
    @DisplayName("Вход через «Личный кабинет»")
    @Description("Проверяем вход через ссылку личного кабинета")
    public void loginFromAccountLink() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountLink();

        loginAndCheckSuccess();
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    @Description("Проверяем переход к входу со страницы регистрации")
    public void loginFromRegistrationPage() {
        RegistrationPage registrationPage =
                new RegistrationPage(driver);

        registrationPage.open();
        registrationPage.clickLoginLink();

        loginAndCheckSuccess();
    }

    @Test
    @DisplayName("Вход через форму восстановления пароля")
    @Description("Проверяем переход к входу со страницы восстановления пароля")
    public void loginFromForgotPasswordPage() {
        ForgotPasswordPage forgotPasswordPage =
                new ForgotPasswordPage(driver);

        forgotPasswordPage.open();
        forgotPasswordPage.clickLoginLink();

        loginAndCheckSuccess();
    }
}