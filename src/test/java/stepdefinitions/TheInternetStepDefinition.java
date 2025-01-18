package stepdefinitions;

import org.junit.Assert;

import io.cucumber.java.en.*;
import workflows.SeleniumWorkFlow;

public class TheInternetStepDefinition {
	SeleniumWorkFlow workFlow = new SeleniumWorkFlow();

	@Then("^verify text Add_Remove in the internet$")
	public void thenVerifyTextAddRemoveInTheInternet() {
		boolean isTextPresent = workFlow.verifyTextInLink(0, "The Internet", "The Internet.Add_RemoveButtonXPATH",
				"XPATH");
		Assert.assertTrue(isTextPresent);
	}

	@Then("^verify displayed Add_Remove in the internet$")
	public void thenVerifyDisplayedAddRemoveInTheInternet() {
		boolean isDisplayed = workFlow.verifyTextInLink(0, "The Internet", "The Internet.Add_RemoveButtonXPATH",
				"XPATH");
		Assert.assertTrue(isDisplayed);
	}

	@Then("^verify disabled textbox in the internet$")
	public void thenVerifyDisabledTextboxInTheInternet() {
		boolean isDisabled = workFlow.verifyDisabled(0, "The Internet", "The Internet.textboxTextBoxXPATH", "XPATH");
		Assert.assertTrue(isDisabled);
	}

	@Then("^verify if visible Enable_disable in the internet$")
	public void thenVerifyIfVisibleEnableDisableInTheInternet() {
		boolean isVisible = workFlow.verifyIfVisible(0, "The Internet", "The Internet.Enable_disableButtonXPATH",
				"XPATH");
		Assert.assertTrue(isVisible);
	}

	@When("^I clicked Enable_disable in the internet$")
	public void whenIClickedEnableDisableInTheInternet() throws Exception {
		workFlow.clickedElement(0, "The Internet", "The Internet.Enable_disableButtonXPATH", "XPATH");
	}

	@When("^I wait in seconds in the internet as '(.*)'$")
	public void whenIWaitInSecondsInTheInternet(String waitTime) {
		workFlow.waitInSeconds(waitTime, 0, "The Internet", "The Internet.waitTextBoxID", "ID");
	}

	@Then("^verify enabled textbox in the internet$")
	public void thenVerifyEnabledTextboxInTheInternet() {
		boolean isEnabled = workFlow.verifyEnabled(0, "The Internet", "The Internet.textboxTextBoxXPATH", "XPATH");
		Assert.assertTrue(isEnabled);
	}

	@Then("^verify URL page in the internet as '(.*)'$")
	public void thenVerifyUrlPageInTheInternet(String expectedUrl) {
		boolean isUrlCorrect = workFlow.verifyURL(expectedUrl, 0, "The Internet", "The Internet.pagePageXPATH",
				"XPATH");
		Assert.assertTrue(isUrlCorrect);
	}

	@Then("^'(.*)' is displayed with '(.*)'$")
	public void thenPageIsDisplayedWithContent(String page, String content) {
		boolean isPageDisplayed = workFlow.VerifyDefaultpageIsdisplayed(page);
		boolean isContentDisplayed = workFlow.VerifyDefaultpageIsdisplayed(content);
		Assert.assertTrue(isPageDisplayed && isContentDisplayed);
	}

}
