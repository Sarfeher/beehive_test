package org.example;

import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DeleteBeeFromTheHiveTest {
    private final String beeNameInJson = String.format("{\"name\": %s}", "Pollenator");
    private final String urlForBees = "http://127.0.0.1:3000/api/bees";
    private final String resBodyMsg = "flew away from the hive!";



    @Test
    public void change_bee_name() {
        given().
                contentType("application/json").
                body(beeNameInJson).
                when().
                delete(urlForBees).
                then().
                assertThat().statusCode(HttpStatus.SC_OK).
                assertThat().body(containsString(resBodyMsg));
    }
}
