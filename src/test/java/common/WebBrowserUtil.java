package common;

import java.util.*;
import java.util.stream.IntStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

public class WebBrowserUtil {
	static WebDriver driver;
	static String path = System.getProperty("user.dir");

	public static boolean isElementContainingTextPresent(String message) {
		driver = WebBrowserLancher.getDriver(); // Obtain the WebDriver instance.
		List<WebElement> webElements = driver.findElements(By.xpath("//*[contains(text(), '" + message + "')]"));
		for (WebElement element : webElements) {
			if (element != null && element.isDisplayed()) {
				return true; // Return true if a visible element is found.
			}
		}
		return false; // Return false if no such element is found.
	}

	public static boolean verifyURL(String url) {
		try {
			driver = WebBrowserLancher.getDriver();
			if (driver.getCurrentUrl().equalsIgnoreCase(url)) {
				return true;
			}
			Thread.sleep(500);
			for (String windowHandle : driver.getWindowHandles()) {
				driver.switchTo().window(windowHandle);
				if (driver.getCurrentUrl().equalsIgnoreCase(url)) {
					return true;
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.err.println("Thread interrupted: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error verifying URL: " + e.getMessage());
		}
		return false;
	}

	public static boolean verifyLabelDisplayed(WebElement element) {
		return element != null;
	}

	public static void ScrollAndEnterText(WebElement element, String text) throws Exception {
		Scroll(element);
		try {
			Thread.sleep(1000);
			element.clear();
			element.sendKeys(text);
		} catch (Exception ex) {
			throw new Exception("Scroll And EnterText unsuccessful" + ex.getMessage());
		}
	}

	public static WebElement findElement(String xpath, String identificationType) throws Exception {
		WebDriver driver = WebBrowserLancher.getDriver();
		WebElement element = null;
		if (xpath.contains("||")) {
			String[] xpathSplit = xpath.split("\\|\\|");
			xpath = xpathSplit[0];
		}
		int i = 0;
		try {
			if (identificationType.equalsIgnoreCase("xpath")) {
				element = driver.findElement(By.xpath(xpath));
			} else if (identificationType.equalsIgnoreCase("id")) {
				element = driver.findElement(By.id(xpath));
			}
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].style.border='2px solid red'", element);
		} catch (Exception e) {
			while (i < 4) {
				try {
					Thread.sleep(2000);
					if (identificationType.equalsIgnoreCase("xpath")) {
						element = driver.findElement(By.xpath(xpath));
					} else if (identificationType.equalsIgnoreCase("id")) {
						element = driver.findElement(By.id(xpath));
					}
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].style.border='2px solid red'", element);
					break;
				} catch (Exception retryException) {
					i++;
					if (i == 4) {
						throw new Exception("Failed to find element after retries", retryException);
					}
				}
			}
		}
		if (xpath.contains("shadowRoot")) {
			xpath = "return " + xpath;
			element = (WebElement) ((JavascriptExecutor) driver).executeScript(xpath);
		}
		return element;
	}

	public static WebElement findElement(String xpath, String identificationType, WebDriver frame) {
		WebElement element = null;
		if (identificationType.toUpperCase().equals("xpath".toUpperCase())) {
			element = frame.findElement(By.xpath(xpath));
		} else if (identificationType.toUpperCase().equals("id".toUpperCase())) {
			element = frame.findElement(By.id(xpath));
		}
		return element;
	}

	public static List<WebElement> findElements(String xpath, String identificationType) {
		driver = WebBrowserLancher.getDriver();
		if (xpath.contains("||")) {
			String[] xpathSplit = xpath.split("\\|\\|");
			xpath = xpathSplit[0];
		}
		List<WebElement> element = null;
		if (identificationType.toUpperCase().equals("xpath".toUpperCase())) {
			element = driver.findElements(By.xpath(xpath));
		} else if (identificationType.toUpperCase().equals("id".toUpperCase())) {
			element = driver.findElements(By.id(xpath));
		}
		return element;
	}

	public static List<WebElement> findElements(String xpath, String identificationType, WebDriver frame) {
		List<WebElement> element = null;
		if (identificationType.toUpperCase().equals("xpath".toUpperCase())) {
			element = frame.findElements(By.xpath(xpath));
		} else if (identificationType.toUpperCase().equals("id".toUpperCase())) {
			element = frame.findElements(By.id(xpath));
		}
		return element;
	}

	public static void EnterText(WebElement element, String text) throws Exception {
		try {
			element.click();
			element.sendKeys(text);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage(), ex);
		}
	}

	public static WebDriver SwitchToBrowserWindow(String windowHandle) {
		driver = WebBrowserLancher.getDriver();
		return driver.switchTo().window(windowHandle);

	}

	public static void Scroll(WebElement element) throws Exception {
		try {
			driver = WebBrowserLancher.getDriver();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(800);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).build().perform();
		} catch (Exception ex) {
			throw new Exception("Scroll and click unsuccessful" + ex.getMessage());
		}
	}

	public static List<WebElement> getElementByXpath(String xpath) {
		if (xpath.contains("||")) {
			String[] xpathSplit = xpath.split("\\|\\|");
			xpath = xpathSplit[0];
		}
		return driver.findElements(By.xpath(xpath));

	}

	public static boolean isStringInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public static boolean IsSorted(List<Integer> sortedList) {
		boolean isSorted = IntStream.range(1, sortedList.size())
				.map(index -> sortedList.get(index - 1).compareTo(sortedList.get(index))).allMatch(order -> order <= 0);
		return isSorted;
	}

	public static boolean IsReverseSorted(List<Integer> sortedList) {
		boolean isSorted = IntStream.range(1, sortedList.size())
				.map(index -> sortedList.get(index - 1).compareTo(sortedList.get(index))).allMatch(order -> order >= 0);
		return isSorted;
	}

	public static String Text(WebElement element) {

		return element.getAttribute("value");

	}

	public static boolean Checked(WebElement _checkBox) {
		return _checkBox.isSelected();
	}

	public static WebElement GetLinkByPartialLinkText(String text) {
		return driver.findElement(By.partialLinkText(text));
	}

	public static void Check(WebElement element) throws Exception {
		if (!Checked(element)) {
			Click(element);
		}
	}

	public static void UnCheck(WebElement element) throws Exception {
		if (Checked(element)) {
			Click(element);
		}
	}

	public static boolean IsEnabled(WebElement element) {

		return element.isEnabled();

	}

	public static boolean IsDisplayed(WebElement element) {

		return element.isDisplayed();

	}

	public static boolean ReadOnly(WebElement element) {

		return element.isSelected();

	}

	public static void ClearText(WebElement element) {
		element.clear();
	}

	public static String IsReadOnly(WebElement element) {
		return element.getAttribute("readonly");

	}

	public static void RightClick(WebElement element) {
		driver = WebBrowserLancher.getDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.contextClick(element).build().perform();

	}

	public static void DoubleClick(WebElement element) {
		driver = WebBrowserLancher.getDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.doubleClick(element).build().perform();

	}

	public static void ScrollAndClearEnterText(WebElement element, String text) throws Exception {
		Scroll(element);
		try {
			element.clear();
			Thread.sleep(1000);
			element.sendKeys(text);
		} catch (Exception ex) {
			throw new Exception("Scroll And Clear EnterText unsuccessful" + ex.getMessage());
		}
	}

	public static void Click(WebElement element) throws Exception {
		try {
			driver = WebBrowserLancher.getDriver();
			try {
				element.click();
			} catch (Exception ex) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
			}
		} catch (Exception ex) {
			throw new Exception("Click unsuccessful" + ex.getMessage());
		}
	}

	public static void ClearAndEnterText(WebElement element, String text) throws Exception {
		try {
			element.click();
			element.clear();
			element.sendKeys(text);
		} catch (Exception ex) {
			throw new Exception("Clear And EnterText unsuccessful" + ex.getMessage());
		}
	}

}