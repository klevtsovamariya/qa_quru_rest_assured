package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class SelenoidTests extends BaseTest {

    @DisplayName("Ответ содержит ожидаемое сообщение о версии Selenoid")
    @Test
    public void textVerificationTest() {
        given()
                .log().all()
                .auth().basic("user1", "1234")
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/response_schema.json"))
                .body("value.message", containsString("Selenoid 1.11.3 built at"));
    }

    @DisplayName("В ответе поле ready равно true")
    @Test
    public void readyVerificationTest() {
        given()
                .log().all()
                .auth().basic("user1", "1234")
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/response_schema.json"))
                .body("value.ready", is(true));
    }

    @DisplayName("Поле message — непустая строка")
    @Test
    public void requiredKeysTest() {
        var response = given()
                .log().all()
                .auth().basic("user1", "1234")
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(200)
                .body("value.message", is(notNullValue()));
    }

    @DisplayName("Запрос без авторизации возвращает 401")
    @Test
    public void unauthorizedTest() {
        given()
                .log().all()
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(401);
    }

    @DisplayName("Запрос с неверным логином возвращает 401")
    @Test
    public void wrongLoginTest() {
        given()
                .log().all()
                .auth().basic("wrong", "1234")
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(401);
    }

    @DisplayName("Запрос с неверным url возвращает 404")
    @Test
    public void notFoundTest() {
        given()
                .log().all()
                .auth().basic("user1", "1234")
                .when()
                .get("/not_found")
                .then()
                .log().all()
                .statusCode(404);
    }
}