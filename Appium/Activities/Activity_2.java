package activities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Driver;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity_2 {
	AppiumDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {

		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.android.chrome");
		options.setAppActivity("com.google.android.apps.chrome.Main");
		options.noReset();

		// Appium server url
		URL serverUrl = new URI("http://127.0.0.1:4723").toURL();

		// Initialize the android driver
		driver = new AndroidDriver(serverUrl, options);
		// Open the page in Chrome
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.get("https://training-support.net");

	}

	@Test
	public void testHeading() {
		String pageHeading = wait.until(ExpectedConditions
				.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Training Support']")))
				.getText();

		// =
		// driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Training
		// Support']")).getText();
		Assert.assertEquals(pageHeading, "Training Support");
		driver.findElement(AppiumBy.accessibilityId("About Us")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='About Us']")));
		String aboutUsHeading = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='About Us']"))
				.getText();
		Assert.assertEquals(aboutUsHeading, "About Us");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
