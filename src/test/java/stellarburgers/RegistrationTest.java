package stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import stellarburgers.page.LoginPage;
import stellarburgers.page.RegistrationPage;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    private String createdEmail;
    private final String password = "password123";

    @After
    public void deleteCreatedUser() {
        if (createdEmail != null) {
            UserApi.deleteUser(createdEmail, password);
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Пользователь с корректными данными успешно регистрируется")
    public void successfulRegistration() {
        createdEmail =
                "user-" + UUID.randomUUID() + "@example.com";

        RegistrationPage registrationPage =
                new RegistrationPage(driver);

        registrationPage.open();
        registrationPage.register(
                "Aiman",
                createdEmail,
                password
        );

        LoginPage loginPage = new LoginPage(driver);

        assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test
    @DisplayName("Ошибка регистрации с коротким паролем")
    @Description("Пароль короче шести символов вызывает ошибку")
    public void registrationWithShortPasswordShowsError() {
        String email =
                "user-" + UUID.randomUUID() + "@example.com";

        RegistrationPage registrationPage =
                new RegistrationPage(driver);

        registrationPage.open();
        registrationPage.register(
                "Aiman",
                email,
                "12345"
        );

        assertTrue(
                registrationPage
                        .isIncorrectPasswordMessageDisplayed()
        );
    }
}
