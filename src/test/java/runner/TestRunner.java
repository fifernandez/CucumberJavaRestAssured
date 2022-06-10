package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"support"},
        monochrome = true,
        //dryRun = false,
        plugin = {
                "pretty",
                "json:build/reports/cucumber.json",
                "junit:build/reports/cucumber.xml",
                "rerun:build/reports/rerun.txt",
                "html:build/reports/cucumber-report.html"
        },
        tags = ""
        )
public class TestRunner {
}
