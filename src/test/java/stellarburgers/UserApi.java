package stellarburgers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import stellarburgers.model.User;
import stellarburgers.model.UserCredentials;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;

public class UserApi {

    private static final String BASE_URL =
            "https://stellarburgers.education-services.ru";

    private UserApi() {
    }

    @Step("Создать пользователя через API")
    public static void createUser(
            String email,
            String password,
            String name
    ) {
        User user = new User(email, password, name);

        given()
                .baseUri(BASE_URL)
                .contentType(JSON)
                .body(user)
                .post("/api/auth/register")
                .then()
                .statusCode(SC_OK);
    }

    @Step("Удалить пользователя через API")
    public static void deleteUser(
            String email,
            String password
    ) {
        UserCredentials credentials =
                new UserCredentials(email, password);

        Response loginResponse = given()
                .baseUri(BASE_URL)
                .contentType(JSON)
                .body(credentials)
                .post("/api/auth/login");

        if (loginResponse.statusCode() == SC_OK) {
            String token =
                    loginResponse.path("accessToken");

            given()
                    .baseUri(BASE_URL)
                    .header("Authorization", token)
                    .delete("/api/auth/user");
        }
    }
}