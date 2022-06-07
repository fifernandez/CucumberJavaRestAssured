package support.steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.junit.Assert;
import util.EndpointsURI;
import util.Environment;
import util.Users;

import java.net.URI;

public class GeneralSteps {
    private Scenario scenario;
    private Response response;
    //private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private String BASE_PATH = EndpointsURI.getURI("users");

    @Before
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }

    @Given("I do a get to the {string} endpoint")
    public void get_call_to_endpoint(String endpoint) throws Exception {
        Assert.assertTrue(String.format("Endpoint '%s' is not defined in the json file.", endpoint), EndpointsURI.endpointDefined(endpoint));
        BASE_PATH = EndpointsURI.getURI(endpoint);
        RequestSpecification req = RestAssured.given();
        response = req.when().get(new URI(BASE_PATH));
    }

    @Then("the returned status code is: {string}")
    public void response_is_validated(String responseMessage) throws InterruptedException {
        int responseCode = response.then().extract().statusCode();
        Assert.assertEquals(responseMessage, String.valueOf(responseCode));
    }

    @Then("the response contains {string} items")
    public void responseIsArrayWith(String arg0) {
        //response.then().statusCode(200);
        response = response.then().extract().response();
        //scenario.log("Response Received == " + response.asPrettyString());
        JSONArray resJson = new JSONArray(response.asString());
        Assert.assertEquals(resJson.length() + "", arg0);
    }

}

