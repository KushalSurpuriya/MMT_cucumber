package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features= {".//Features/MakeMytrip.feature"}, 
				 glue="StepDefination",
				 //tags="@Sanity",
				 plugin= {"pretty", "html:reports/myreport_html.html",
						 "json:reports/myreport_json.json",
						 "junit:reports/myreport_xml.xml",
						 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
				 )
public class TestRun{

}
