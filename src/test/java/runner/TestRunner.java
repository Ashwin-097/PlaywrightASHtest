package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "steps",
    plugin = {
        "pretty",
        "html:target/cucumber-report.html",
        "junit:target/cucumber-report.xml",
        "json:target/cucumber-report.json"
    },
    tags = "@test",
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}



// features = "src/test/resources/features",//