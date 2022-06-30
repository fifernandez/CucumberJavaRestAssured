package support;

import io.cucumber.java.*;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.io.output.WriterOutputStream;
import config.Configuration;
import util.CurlParser;
import util.testrail.TestRailsLogger;
import java.io.PrintStream;
import java.io.StringWriter;

public class Hooks {

    @BeforeAll
    public static void setUp(){
        Configuration.loadAllConfigs();
        //RestAssured.port = "";
        //RestAssured.basePath = "";
        RestAssured.baseURI = BasePath.getBasePath();
    }
    StringWriter requestWriter = new StringWriter();
    StringWriter responseWriter = new StringWriter();

    @Before
    public void beforeScenario(Scenario scenario){
        PrintStream requestCapture = new PrintStream(new WriterOutputStream(requestWriter, "UTF-8"), true);
        PrintStream responseCapture = new PrintStream(new WriterOutputStream(responseWriter, "UTF-8"), true);
        RestAssured.filters(new RequestLoggingFilter(requestCapture), new ResponseLoggingFilter(responseCapture));
    }

    @After
    public void afterScenario(Scenario scenario){
        if (scenario.isFailed()) {
            String request = requestWriter.toString();
            String curl = CurlParser.parseCurl(request);
            scenario.attach("Request: \n" + request, "text/plain","Request");
            scenario.attach(curl, "text/plain","Curl");
            scenario.attach("Response: \n" + responseWriter.toString(), "text/plain","Response");
            TestRailsLogger.logResultToTestRail(scenario, curl);
        } else {
            TestRailsLogger.logResultToTestRail(scenario, "");
        }
    }

}
