package project;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

public class Activity_5 {
	AndroidDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.android.chrome");
		options.setAppActivity("com.google.android.apps.chrome.Main");
		options.setCapability("chromedriver_autodownload", true);
		options.noReset();

		URL serverURl = new URI("http://127.0.0.1:4723").toURL();
		driver = new AndroidDriver(serverURl, options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.get("https://training-support.net/webelements");
	}

	// --- Test Cases ---

	@Test(priority = 1)
	public void testValidCredentials() {
		performLogin("admin", "password");

		// Assertion for Success
		String message = wait.until(ExpectedConditions
				.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Login Success!']")))
				.getText();

		Assert.assertEquals(message, "Login Success!");
	}

	@Test(priority = 2)
	public void testInvalidCredentials() {
		// Refresh/Re-navigate to reset the form state
		driver.get("https://training-support.net/webelements");
		performLogin("wrongUser", "wrongPass");
		// Assertion for Failure
		String errorMessage = wait.until(ExpectedConditions
				.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='subheading']")))
				.getText();

		Assert.assertEquals(errorMessage, "Invalid credentials");
	}

	// --- Login ---
	public void performLogin(String username, String password) {
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.className("android.webkit.WebView")));

		driver.findElement(AppiumBy.androidUIAutomator(
			    "new UiScrollable(new UiSelector().scrollable(true))" +
			    ".scrollIntoView(new UiSelector().textContains(\"Login Form\"))"
			)).click();

		// Enter Fields
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='username']")))
				.sendKeys(username);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='password']")))
				.sendKeys(password);
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Submit']")).click();

		// Optional: Dismiss the Chrome data breach/leak warning
		try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.Button[@resource-id='com.android.chrome:id/positive_button']"))).click();
        } catch (Exception e) {
			System.out.println("Chrome dialog did not appear. Skipping...");
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
