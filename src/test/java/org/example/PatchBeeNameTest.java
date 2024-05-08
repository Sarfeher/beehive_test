package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class PatchBeeNameTest {

    private final TestUtil testUtil = new TestUtil();
    private final Dotenv dotenv = Dotenv.load();
    private final String beeNameInJson = String.format("{\"name\": \"%s\"}", "Honey Bunny");

    @Test
    public void changeBeeName() {
        String urlForBees = dotenv.get("URL_FOR_BEES");
        String urlForTheSecondBee = dotenv.get("URL_FOR_SECOND_BEE");
        String resBodyMsg = "change its name to";

        testUtil.postBee("Zsófi", urlForBees);
        given().
                contentType("application/json").
                body(beeNameInJson).
                when().
                patch(urlForTheSecondBee).
                then().
                assertThat().statusCode(HttpStatus.SC_OK).
                assertThat().body(containsString(resBodyMsg));
        testUtil.deleteBee("Honey Bunny", urlForBees);
    }
}
