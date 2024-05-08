package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DeleteBeeFromTheHiveTest {
    private final String beeNameInJson = String.format("{\"name\": \"%s\"}", "Pollenator");
    private final Dotenv dotenv = Dotenv.load();
    private final String urlForBees = dotenv.get("URL_FOR_BEES");
    private final String resBodyMsg = "flew away from the hive!";



    @Test
    public void deleteBee() {
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
