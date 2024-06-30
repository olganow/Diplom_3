package user;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.NonNull;

import static constants.Constants.*;
import static io.restassured.RestAssured.given;

public class UserApi {

    public ValidatableResponse createUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(HOME_URL)
                .body(user)
                .when()
                .post(AUTH_REGISTER_API)
                .then();
    }

    public ValidatableResponse deleteUser(@NonNull String token) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(HOME_URL)
                .auth().oauth2(token)
                .when()
                .delete(AUTH_USER_API)
                .then();
    }

    public ValidatableResponse loginUser(User credentials) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(HOME_URL)
                .body(credentials)
                .when()
                .post(AUTH_LOGIN_API)
                .then();
    }

}
