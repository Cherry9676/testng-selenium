package common;

import java.util.*;
import java.util.logging.Level;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;

public class WebBrowserLancher {
	private static WebDriver driver;
	private static String path = System.getProperty("user.dir");

	public static WebDriver getDriver() {
		return driver;
	}

	@SuppressWarnings("deprecation")
	public static void launchApplication() {

		// Read values from XML (assumed)
		String url = CommonUtil.getXMLTagValue("URL");
		String browsertype = CommonUtil.getXMLTagValue("BrowserType").toUpperCase();
		String pageLoadTimeout = CommonUtil.getXMLTagValue("PageLoadTime");
		String dockerHost = CommonUtil.getXMLTagValue("DockerHost");
		String dockerPortStr = CommonUtil.getXMLTagValue("DockerPort"); // Get port from XML
		int port = (dockerPortStr != null && !dockerPortStr.isEmpty()) ? Integer.parseInt(dockerPortStr) : -1;

		if (port != -1 && dockerHost != null && !dockerHost.isEmpty()) {
			// Define the URL of the Selenium server running in Docker container
			String seleniumURL = "http://" + dockerHost + ":" + port + "/wd/hub";
			System.out.println("Connecting to Selenium server in Docker at: " + seleniumURL);

			if (browsertype.contains("FIREFOX")) {
				FirefoxOptions options = new FirefoxOptions();
				if (browsertype.contains("HEADLESS")) {
					options.addArguments("--headless");
					options.addArguments("--window-size=1920x1080");
				}
				try {
					driver = new RemoteWebDriver(new URL(seleniumURL), options);
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException("Host and port are not valid");
				}
			} else if (browsertype.contains("EDGE")) {
				EdgeOptions options = new EdgeOptions();
				if (browsertype.contains("HEADLESS")) {
					options.addArguments("--headless");
					options.addArguments("--window-size=1920x1080");
				}
				try {
					driver = new RemoteWebDriver(new URL(seleniumURL), options);
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException("Host and port are not valid");
				}
			} else if (browsertype.contains("CHROME")) {
				ChromeOptions options = new ChromeOptions();
				if (browsertype.contains("HEADLESS")) {
					options.addArguments("--headless");
					options.addArguments("window-size=1920,1080");
					options.addArguments("--disable-gpu");
				}
				try {
					driver = new RemoteWebDriver(new URL(seleniumURL), options);
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException("Host and port are not valid");
				}
			} else {
				throw new IllegalArgumentException("Unsupported browser type: " + browsertype);
			}

		} else {
			// Fallback: Run tests locally if Docker is not configured or is unavailable
			if (browsertype.contains("FIREFOX")) {
				WebDriverManager.firefoxdriver().clearDriverCache().setup();
				FirefoxOptions options = new FirefoxOptions();
				if (browsertype.contains("HEADLESS")) {
					options.addArguments("--headless");
					options.addArguments("--window-size=1920x1080");
				}
				driver = new FirefoxDriver(options);
			} else if (browsertype.contains("EDGE")) {
				WebDriverManager.edgedriver().clearDriverCache().setup();
				EdgeOptions options = new EdgeOptions();
				if (browsertype.contains("HEADLESS")) {
					options.addArguments("--headless");
					options.addArguments("--window-size=1920x1080");
				}
				driver = new EdgeDriver(options);
			} else if (browsertype.contains("CHROME")) {
				WebDriverManager.chromedriver().clearDriverCache().setup();
				ChromeOptions options = new ChromeOptions();
				if (browsertype.contains("HEADLESS")) {
					options.addArguments("--headless");
					options.addArguments("window-size=1920x1080");
					options.addArguments("--disable-gpu");
				}
				driver = new ChromeDriver(options);
			} else {
				throw new IllegalArgumentException("Unsupported browser type: " + browsertype);
			}
		}

		// Common setup for the driver
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(pageLoadTimeout)));
		driver.get(url);
	}

	// Method to switch tab based on either tab number or partial title text.
	public static void tabSwitch(String identifier) {
		List<String> tabs = new ArrayList<>(driver.getWindowHandles()); // Get all window handles (tabs)
		try {
			int tabIndex = Integer.parseInt(identifier);
			if (tabIndex >= 0 && tabIndex < tabs.size()) {
				driver.switchTo().window(tabs.get(tabIndex)); // Switch to the tab at the specified index
			}
		} catch (NumberFormatException e) {
			boolean found = false;
			for (String handle : tabs) {
				driver.switchTo().window(handle);
				if (driver.getTitle().contains(identifier)) {
					found = true;
					break; // Stop once the tab with the matching title is found
				}
			}
		}
	}

	public static void closeBrowserInstance() {
		try {
			if (driver == null) {
				System.out.println("Driver is null. No browser instance to close.");
				return; // Exit the method if driver is null
			}
			driver.quit();
		} catch (Exception e) {
		} finally {
			driver = null;
		}
	}

}
