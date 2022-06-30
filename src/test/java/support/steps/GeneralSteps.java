package support.steps;

import java.net.URI;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import support.Endpoints;

public class GeneralSteps {
    private Scenario scenario;
    private Response response;
    private String BASE_PATH = Endpoints.getURI("users");
    @Before
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }

    @Given("I do a get to the {string} endpoint")
    public void get_call_to_endpoint(String endpoint) throws Exception {
        Assert.assertTrue(String.format("Endpoint '%s' is not defined in the json file.", endpoint), Endpoints.endpointDefined(endpoint));
        BASE_PATH = Endpoints.getURI(endpoint);
        RequestSpecification req = RestAssured.given();
        response = req.when().get(new URI(BASE_PATH));
    }

    @Then("the returned status code is: {string}")
    public void response_is_validated(String responseMessage) throws InterruptedException {
        int responseCode = response.then().extract().statusCode();
        Assert.assertEquals(responseMessage, String.valueOf(responseCode));
    }

    @Then("the response contains {string} items")
    public void responseIsArrayWith(String size) {
        //response.then().statusCode(200);
        response = response.then().extract().response();
        JSONArray resJson = new JSONArray(response.asString());
        Assert.assertEquals(resJson.length() + "", size);
    }

    @Given("I do a get to the {string} endpoint just to test with bad parameters")
    public void iDoAGetToTheEndpointJustToTestWithBadParameters(String endpoint)  throws Exception {
        Assert.assertTrue(String.format("Endpoint '%s' is not defined in the json file.", endpoint), Endpoints.endpointDefined(endpoint));
        BASE_PATH = Endpoints.getURI(endpoint);
        RequestSpecification req = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", "test_rest");
        requestParams.put("password", "Testrest@123");
        response = req.header("U", "MyAppName")
                .header("Agent", "MyAppName")
                .queryParam("q1=1")
                .queryParam("q2=2")
                .body(requestParams.toString(1))
                .when().get(new URI(BASE_PATH));
    }
}

