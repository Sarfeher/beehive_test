package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class GetBeehiveTest {
    private final TestUtil testUtil = new TestUtil();
    private final Dotenv dotenv = Dotenv.load();
    private final String urlForBees = dotenv.get("URL_FOR_BEES");
    private final String urlForHive = dotenv.get("URL_FOR_HIVE");
    private final String beeName = "Zsófi";


    @Test
    public void getTheBeehive() {

        testUtil.postBee(beeName, urlForBees);

        when().
                get(urlForHive)
                .then()
                .assertThat().statusCode(200)
                .assertThat().contentType("application/json");

        testUtil.deleteBee(beeName, urlForBees);
    }
}
