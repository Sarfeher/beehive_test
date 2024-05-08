package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class GetBeehiveTest {
    private final TestUtil testUtil = new TestUtil();
    private final Dotenv dotenv = Dotenv.load();
    private final String urlForBees = dotenv.get("URL_FOR_BEES");
    private final String urlForHive = dotenv.get("URL_FOR_HIVE");


    @Test
    public void getTheBeehive() {

        testUtil.postBee("Zsófi", urlForBees);

        when().
                get(urlForHive)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .assertThat().contentType("application/json");

        testUtil.deleteBee("Zsófi", urlForBees);
    }
}
