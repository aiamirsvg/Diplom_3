package stellarburgers;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class UserApi {

    private static final String BASE_URL =
            "https://stellarburgers.education-services.ru";

    private UserApi() {
    }

    public static void deleteUser(String email, String password) {
        String requestBody = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\"}",
                email,
                password
        );

        Response loginResponse = given()
                .baseUri(BASE_URL)
                .contentType(JSON)
                .body(requestBody)
                .post("/api/auth/login");

        if (loginResponse.statusCode() == 200) {
            String token = loginResponse.path("accessToken");

            given()
                    .baseUri(BASE_URL)
                    .header("Authorization", token)
                    .delete("/api/auth/user");
        }
    }
    public static void createUser(
            String email,
            String password,
            String name
    ) {
        String requestBody = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}",
                email,
                password,
                name
        );

        given()
                .baseUri(BASE_URL)
                .contentType(JSON)
                .body(requestBody)
                .post("/api/auth/register")
                .then()
                .statusCode(200);
    }
}