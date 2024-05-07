package org.example;

import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class GetBeehiveTest {
    private final TestUtil testUtil = new TestUtil();
    final String urlForBees = "http://127.0.0.1:3000/api/bees";
    final String urlForHive = "http://127.0.0.1:3000/api/beehive";


    @Test
    public void beehive_get_status_code_is_200() {

        testUtil.postBee("Zsófi", urlForBees);

        when().
                get(urlForHive)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .assertThat().contentType("application/json");

        testUtil.deleteBee("Zsófi", urlForBees);
    }
}
