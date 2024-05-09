package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class PostIntoBeehiveTest {

    private final TestUtil testUtil = new TestUtil();
    private final Dotenv dotenv = Dotenv.load();
    private final String beeNameInJson = String.format("{\"name\": \"%s\"}", "Pollenator");
    private final String urlForBees = dotenv.get("URL_FOR_BEES");


    @Test
    public void createNewBee() {
        String resBodyMsg = "flew into the hive!";
        given().
                contentType("application/json").
                body(beeNameInJson).
                when().
                post(urlForBees).
                then().
                assertThat().statusCode(201).
                assertThat().body(containsString(resBodyMsg));
        testUtil.deleteBee("Pollenator", urlForBees);

    }
}
