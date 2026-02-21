package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = { "stepdefinitions","hooks" },
        plugin = {
                "pretty",
                "html:build/reports/cucumber.html",
                "json:build/reports/cucumber.json"
        },
        monochrome = true
        // tags = "@smoke"   // uncomment if you want to run by tag
)
public class TestRunner {
}
