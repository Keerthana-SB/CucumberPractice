package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/main/java/feature/carWale.feature"}, glue = {"steps"}, monochrome = true, 
                             snippets=SnippetType.CAMELCASE)

public class RunnerFile extends AbstractTestNGCucumberTests{
	

}
