package steps;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.io.IOException;

public class APIwithtokenJunit {
    Playwright playwright;
    APIRequestContext requestContext;
    APIResponse apiPostTokenResponse;
    JsonNode postJsonResponse;

    @Before //Hook - runs before each scenario
    public void setup() {
        playwright = Playwright.create();
        requestContext = playwright.request().newContext();
    }

    @After
    public void tearDown() {
        requestContext.dispose();
        playwright.close();
    }

    @Given("I have a valid login payload")
    public String getLoginPayload() {
        return "{\n" +
               "    \"username\" : \"admin\",\n" +
               "    \"password\" : \"password123\"\n" +
               "}";
    }

    @When("I send a POST request to the auth endpoint")
    public void sendPostRequest() throws IOException {
        String reqTokenJsonBody = getLoginPayload();
        apiPostTokenResponse = requestContext.post("https://restful-booker.herokuapp.com/auth",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(reqTokenJsonBody));

        ObjectMapper objectMapper = new ObjectMapper();
        postJsonResponse = objectMapper.readTree(apiPostTokenResponse.body());
    }

    @Then("I should receive a token in the response")
    public void validateResponse() {
        System.out.println(apiPostTokenResponse.status());
        Assert.assertEquals(200, apiPostTokenResponse.status());
        Assert.assertEquals("OK", apiPostTokenResponse.statusText());

        System.out.println(postJsonResponse.toPrettyString());
        String token = postJsonResponse.get("token").asText();
        Assert.assertNotNull(token);
        System.out.println("Token: " + token);
    }
}