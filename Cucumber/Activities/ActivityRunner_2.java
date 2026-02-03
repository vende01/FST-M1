package testRunners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import io.cucumber.junit.platform.engine.Constants;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("Features")
@ConfigurationParameter(
key = Constants.GLUE_PROPERTY_NAME, value = "stepDefinitions"
)
@ConfigurationParameter(
		  key = Constants.FILTER_TAGS_PROPERTY_NAME,
		  value = "@activity2"
		)
public class ActivityRunner_2 {

}
