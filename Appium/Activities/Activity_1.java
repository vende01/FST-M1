package activities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity_1 {
	AppiumDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {
		// apk file
		File appFile = new File("src/test/resources/Calculator.apk");
		// Desired Capabilities
		UiAutomator2Options cap = new UiAutomator2Options();
		cap.setPlatformName("Android");
		cap.setAutomationName("UiAutomator2");
		cap.setApp(appFile.getAbsolutePath());
		cap.noReset();

		// Appium server url
		URL serverUrl = new URI("http://127.0.0.1:4723").toURL();

		// Initialize the android driver
		driver = new AndroidDriver(serverUrl, cap);

	}

	@Test
	public void testMethod() {
		driver.findElement(AppiumBy.id("digit_2")).click();
		driver.findElement(AppiumBy.accessibilityId("multiply")).click();
		driver.findElement(AppiumBy.id("digit_3")).click();
		driver.findElement(AppiumBy.accessibilityId("equals")).click();
		
		String result = driver.findElement(AppiumBy.id("result_final")).getText();
		Assert.assertEquals(result,"6");



	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
