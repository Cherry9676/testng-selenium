package stepdefinitions;

import io.cucumber.java.en.*;
import workflows.SeleniumWorkFlow;
import org.junit.Assert;
import common.WebBrowserUtil;

public class NewFeatureStepDefinition {
	SeleniumWorkFlow workFlow = new SeleniumWorkFlow();

	@When("^I checked Checkbox1 in the internet$")
	public void WhenICheckedCheckbox1InTheInternet() throws Exception {
		workFlow.checkCheckbox(0, "New Feature", "New Feature.Checkbox1CheckBoxXPATH", "XPATH");
	}

	@Then("^verify checked Checkbox1 in the internet$")
	public void ThenVerifyCheckedCheckbox1InTheInternet() throws Exception {
		Assert.assertTrue(workFlow.verifyChecked(0, "New Feature", "New Feature.Checkbox1CheckBoxXPATH", "XPATH"));
		
	}

	@When("^I unchecked Checkbox1 in the internet$")
	public void WhenIUncheckedCheckbox1InTheInternet() throws Exception {
		workFlow.uncheckCheckBox(0, "New Feature", "New Feature.Checkbox1CheckBoxXPATH", "XPATH");

	}
	
	 @When("^I wait in seconds wait in the internet as '(.*)'$")
	    public void whenIWaitInSecondsInTheInternet(String waitTime) {
	        workFlow.waitInSeconds(waitTime, 0, "The Internet", "The Internet.waitTextBoxID", "ID");
	    }

	@Then("^verify unchecked Checkbox1 in the internet$")
	public void ThenVerifyUncheckedCheckbox1InTheInternet() throws Exception {
		Assert.assertTrue(workFlow.verifyUnchecked(0, "New Feature", "New Feature.Checkbox1CheckBoxXPATH", "XPATH"));
	}
}