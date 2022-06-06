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
import support.BaseClass;

import java.net.URI;

public class GeneralSteps extends BaseClass {

    private Scenario scenario;
    private Response response;
    //private final String BASE_URL = "http://localhost:8888";
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Before
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }

    @Given("get call to {string}")
    public void get_call_to_url(String url) throws Exception {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification req = RestAssured.given();
        response = req.when().get(new URI(url));
    }

    @Then("the returned status code is: {string}")
    public void response_is_validated(String responseMessage) throws InterruptedException {
        int responseCode = response.then().extract().statusCode();
        Assert.assertEquals(responseMessage, String.valueOf(responseCode));
        Thread.sleep(1000);
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

