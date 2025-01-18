package runner;

import org.junit.AfterClass;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

import common.WebBrowserLancher;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = { "stepdefinitions", "common" },//tags = "@test001",
		plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "common.StepListener" })
public class TestRunner {
	public static int retries = 0;

	@AfterClass
	public static void tearDown() {
		

	}

}
