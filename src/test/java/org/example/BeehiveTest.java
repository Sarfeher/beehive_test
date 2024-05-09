package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

public class BeehiveTest {
    private final TestUtil testUtil = new TestUtil();
    private final String beeNameInJson = String.format("{\"name\": \"%s\"}", "Pollenator");
    private final Dotenv dotenv = Dotenv.load();
    private final String urlForBees = dotenv.get("URL_FOR_BEES");
    private final String urlForHive = dotenv.get("URL_FOR_HIVE");
    private final String beeName = "Zsófi";

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


    @Test
    public void getOneBeeById() {

        testUtil.postBee(beeName, urlForBees);

        when().
                get(urlForBees + "/1")
                .then()
                .assertThat().statusCode(200)
                .assertThat().contentType("application/json");

        testUtil.deleteBee(beeName, urlForBees);
    }

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
                assertThat().statusCode(200).
                assertThat().body(containsString(resBodyMsg));
        testUtil.deleteBee("Honey Bunny", urlForBees);
    }

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

