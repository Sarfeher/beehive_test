package org.example;

import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class GetBeehiveTest {
    private final TestUtil testUtil = new TestUtil();
    final String urlForBees = System.getenv("URL_FOR_BEES");
    final String urlForHive = "http://127.0.0.1:3000/api/beehive";


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
