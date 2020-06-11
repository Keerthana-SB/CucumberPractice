package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/java/feature/trivago.feature"}, glue = {"steps"}, monochrome = true, 
snippets=SnippetType.CAMELCASE)
public class RunTest extends AbstractTestNGCucumberTests{

}
