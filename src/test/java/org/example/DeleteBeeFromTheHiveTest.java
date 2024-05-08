package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteBeeFromTheHiveTest {
    private final TestUtil testUtil = new TestUtil();
    private final String beeNameInJson = String.format("{\"name\": \"%s\"}", "Pollenator");
    private final Dotenv dotenv = Dotenv.load();
    private final String urlForBees = dotenv.get("URL_FOR_BEES");



    @Test
    public void deleteBee() {
        testUtil.postBee("Pollenator", urlForBees);
        given().
                contentType("application/json").
                body(beeNameInJson).
                when().
                delete(urlForBees).
                then().
                assertThat().statusCode(204);
    }
}
