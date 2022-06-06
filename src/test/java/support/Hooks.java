package support;

import io.cucumber.java.*;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.io.output.WriterOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;

public class Hooks {
    StringWriter requestWriter = new StringWriter();
    StringWriter responseWriter = new StringWriter();

    @Before
    public void beforeScenario(Scenario scenario){
        PrintStream requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true);
        PrintStream responseCapture = new PrintStream(new WriterOutputStream(responseWriter), true);
        RestAssured.filters(new RequestLoggingFilter(requestCapture), new ResponseLoggingFilter(responseCapture));
    }

    @After
    public void afterScenario(Scenario scenario){
        if (scenario.isFailed()) {
            scenario.log("Request: \n" + requestWriter.toString());
            //scenario.log("Response: \n" + responseWriter.toString());
            RestAssured.given().log();
        }
    }

}
