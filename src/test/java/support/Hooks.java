package support;

import com.google.common.collect.ImmutableMap;
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
import java.util.HashMap;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class Hooks {

    @BeforeAll
    public static void setUp(){
        Configuration.loadAllConfigs();
        //RestAssured.port = "";
        //RestAssured.basePath = "";
        RestAssured.baseURI = BasePath.getBasePath();
        setUpAllureEnv();
    }

    public static void setUpAllureEnv() {
        String tags = System.getProperty("cucumber.filter.tags");
        if (tags == null || tags.isEmpty()) {
            tags = "ALL";
        }
        String testRun = System.getProperty("testRunID");
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("Environment:", Environment.getEnvironment().toUpperCase());
        if (testRun != null) {
            properties.put("Test Run:", "https://project.testrail.io/index.php?/runs/view/" + testRun);
        }
        properties.put("Base url:", BasePath.getBasePath());
        properties.put("Tags:", tags);
        ImmutableMap<String, String> immutableMap = ImmutableMap.copyOf(properties);
        allureEnvironmentWriter(immutableMap, System.getProperty("user.dir") + "/build/allure-results/");
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
            String curl = CurlParser.getCurls(request);
            scenario.attach("Request: \n" + request, "text/plain","Requests");
            scenario.attach(curl, "text/plain","Curls");
            scenario.attach("Response: \n" + responseWriter.toString(), "text/plain","Responses");
            TestRailsLogger.logResultToTestRail(scenario, curl);
        } else {
            TestRailsLogger.logResultToTestRail(scenario, "");
        }
    }

}
