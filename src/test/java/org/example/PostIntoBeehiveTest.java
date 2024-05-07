package org.example;

import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class PostIntoBeehiveTest {

    private final TestUtil testUtil = new TestUtil();
    private final String beeNameInJson = String.format("{\"name\": %s}", "Pollenator");
    private final String urlForBees = "http://127.0.0.1:3000/api/bees";
    private final String resBodyMsg = "flew into the hive!";


    @Test
    public void beehive_create_new_bee_with_name() {
        given().
                contentType("application/json").
                body(beeNameInJson).
                when().
                post(urlForBees).
                then().
                assertThat().statusCode(HttpStatus.SC_CREATED).
                assertThat().body(containsString(resBodyMsg));
        testUtil.deleteBee("Pollenator", urlForBees);

    }
}
