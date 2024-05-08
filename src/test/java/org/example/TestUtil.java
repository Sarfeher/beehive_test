package org.example;
import static io.restassured.RestAssured.given;

public class TestUtil {
    public void deleteBee(String beeName, String URL) {
        String beeNameInJson = String.format("{\"name\": \"%s\"}", beeName);
        given().
                contentType("application/json").
                body(beeNameInJson).
                when().
                delete(URL);
    }

    public void postBee(String beeName, String URL) {
        String beeNameInJson = String.format("{\"name\": \"%s\"}", beeName);
        given().
                contentType("application/json").
                body(beeNameInJson).
                when().
                post(URL);

    }
}
