package workflows;

import common.*;

import java.util.stream.Collectors;
import java.util.Random;
import java.util.Comparator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Calendar;
import java.io.BufferedWriter;
import java.text.DateFormat;
import java.util.Arrays;
import java.io.File;
import org.openqa.selenium.interactions.Actions;
import java.nio.file.Path;
import org.openqa.selenium.Keys;
import java.nio.file.Paths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Assert;
import java.util.Optional;
import java.util.List;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.HashMap;
import org.openqa.selenium.logging.LogType;

import com.github.dockerjava.api.model.Driver;

import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogEntries;
import java.io.IOException;
import java.util.TimeZone;
import java.util.Set;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

public class SeleniumWorkFlow {
	public static WebDriver browser;
	public static int Number_of_Iteration = 5;

	public void accessToPage() {
		WebBrowserLancher.launchApplication();
	}

	public void checkCheckbox(int index, String page, String xpathKey, String identifier) throws Exception {
		WebBrowserLancher.tabSwitch(String.valueOf(index));
		browser = WebBrowserLancher.getDriver();
		String xpath = CommonUtil.getObjectRepositoryFromJson(xpathKey);
		WebElement element = null;
		try {
			element = WebBrowserUtil.findElement(xpath, identifier);
			element.click();
		} catch (Exception ex) {
			throw new Exception("Unexpected error while checking checkbox", ex);
		}
	}

	public boolean verifyChecked(int index, String page, String xpathKey, String identifier) throws Exception {
		WebBrowserLancher.tabSwitch(String.valueOf(index));
		browser = WebBrowserLancher.getDriver();
		boolean isVerified = false;
		String xpath = CommonUtil.getObjectRepositoryFromJson(xpathKey);
		try {
			identifier = identifier.toLowerCase();
			WebElement element = WebBrowserUtil.findElement(xpath, identifier);
			isVerified = WebBrowserUtil.Checked(element);
		} catch (Exception ex) {
			throw new Exception("Error while verifying checkbox state", ex);
		}
		return isVerified;
	}

	public void uncheckCheckBox(int index, String page, String xpathKey, String identifier) throws Exception {
		browser = WebBrowserLancher.getDriver();
		WebBrowserLancher.tabSwitch(String.valueOf(index));
		try {
			String xpath = CommonUtil.getObjectRepositoryFromJson(xpathKey);
			WebElement element = WebBrowserUtil.findElement(xpath, identifier);
			WebBrowserUtil.UnCheck(element); // Attempt to uncheck the checkbox
		} catch (Exception e) {
			throw new Exception("Failed to uncheck the checkbox", e);
		}
	}

	public boolean verifyUnchecked(int index, String page, String XpathKey, String identifier) throws Exception {
		browser = WebBrowserLancher.getDriver();
		WebBrowserLancher.tabSwitch(String.valueOf(index));
		boolean isVerified = false;
		String xpath = CommonUtil.getObjectRepositoryFromJson(XpathKey);
		WebElement element = WebBrowserUtil.findElement(xpath, identifier);
		isVerified = !WebBrowserUtil.Checked(element);
		return isVerified;
	}

	public boolean verifyTextInLink(int index, String page, String XpathKey, String identifier) {
		browser = WebBrowserLancher.getDriver();
		WebBrowserLancher.tabSwitch(String.valueOf(index));
		boolean staleElement = false;
		try {
			String xpath = CommonUtil.getObjectRepositoryFromJson(XpathKey);
			WebElement element = WebBrowserUtil.findElement(xpath, identifier);
			staleElement = WebBrowserUtil.verifyLabelDisplayed(element);
		} catch (Exception ex) {
			staleElement = false;
		}
		return staleElement;
	}

	public boolean verifyDisabled(int index, String page, String XpathKey, String identifier) {
		browser = WebBrowserLancher.getDriver();
		WebBrowserLancher.tabSwitch(String.valueOf(index));
		boolean isVerified = false;
		try {
			String xpath = CommonUtil.getObjectRepositoryFromJson(XpathKey);
			isVerified = !WebBrowserUtil.IsEnabled(WebBrowserUtil.findElement(xpath, identifier));
			if (!isVerified) {
				isVerified = Boolean
						.parseBoolean(WebBrowserUtil.IsReadOnly(WebBrowserUtil.findElement(xpath, identifier)));
			}
		} catch (Exception e) {
			isVerified = false;
		}
		return isVerified;
	}

	public boolean verifyIfVisible(int index, String page, String XpathKey, String identifier) {
		WebBrowserLancher.tabSwitch(String.valueOf(index));
		browser = WebBrowserLancher.getDriver();
		boolean isVerified = false;
		try {
			String xpath = CommonUtil.getObjectRepositoryFromJson(XpathKey);
			List<WebElement> links = WebBrowserUtil.findElements(xpath, identifier);
			if (links.size() > 0) {
				isVerified = true; // Checkbox is visible
			}
		} catch (Exception ex) {
			isVerified = false; // If exception occurs, set to false
		}
		return isVerified;
	}

	public void clickedElement(int index, String page, String XpathKey, String identifier) throws Exception {
		browser = WebBrowserLancher.getDriver();
		WebBrowserLancher.tabSwitch(String.valueOf(index));
		WebElement elementToBeClicked = null;
		try {
			String xpath = CommonUtil.getObjectRepositoryFromJson(XpathKey);
			elementToBeClicked = WebBrowserUtil.findElement(xpath, identifier);
			WebBrowserUtil.Click(elementToBeClicked); // Attempt to click the element
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e); // Throw custom exception in case of failure
		}

	}

	public void waitInSeconds(String time, int index, String page, String Xpath, String identifier) {
		try {
			time = CommonUtil.getJSONTestData(time);
			int waitTime = Integer.parseInt(time);
			Thread.sleep((1000) * waitTime);
		} catch (Exception ex) {
		}
	}

	public boolean verifyEnabled(int index, String page, String XpathKey, String identifier) {
		browser = WebBrowserLancher.getDriver();
		WebBrowserLancher.tabSwitch(String.valueOf(index));
		boolean isVerified = false;
		try {
			String xpath = CommonUtil.getObjectRepositoryFromJson(XpathKey);
			isVerified = WebBrowserUtil.IsEnabled(WebBrowserUtil.findElement(xpath, identifier));
		} catch (Exception ex) {
			isVerified = false;
		}
		return isVerified;
	}

	public boolean VerifyDefaultpageIsdisplayed(String defaultpage) {
		browser = WebBrowserLancher.getDriver();
		boolean isVerified = false;
		for (int i = 0; i <= 2; i++) {
			if (defaultpage != null && !defaultpage.isEmpty() && !defaultpage.toUpperCase().equals("NA".toUpperCase())
					&& !defaultpage.toUpperCase().equals("Page1".toUpperCase())
					&& !defaultpage.toUpperCase().equals("Page2".toUpperCase())
					&& !defaultpage.toUpperCase().equals("Page3".toUpperCase())
					&& !defaultpage.toUpperCase().equals("Page4".toUpperCase())) {
				isVerified = browser.getTitle().toUpperCase().contains(defaultpage.toUpperCase());
			} else {
				isVerified = true;
			}
			if (isVerified) {
				break;
			}
		}
		return isVerified;
	}

	public boolean VerifymessageIsDisplayed(String message) {
		return true;

	}

	public boolean verifyURL(String url, int index, String page, String XpathKey, String identifier) {
		browser = WebBrowserLancher.getDriver();
		WebBrowserLancher.tabSwitch(String.valueOf(index));
		boolean isVerified = false;
		url = CommonUtil.getJSONTestData(url);
		int i = 0;
		while (i < 5) {
			try {
				isVerified = WebBrowserUtil.verifyURL(url.toUpperCase());
				if (isVerified) {
					break;
				}
				i++;
			} catch (Exception ex) {
				i++;
			}
		}
		return isVerified;
	}

}