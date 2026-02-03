package activities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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

public class Activity_4 {
	AppiumDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {
		UiAutomator2Options cap = new UiAutomator2Options();
		cap.setPlatformName("Android");
		cap.setAutomationName("UiAutomator2");
		cap.setAppPackage("com.google.android.contacts");
		cap.setAppActivity("com.android.contacts.activities.PeopleActivity");
		cap.noReset();

		URL serverUrl = new URI("http://127.0.0.1:4723").toURL();
		driver = new AndroidDriver(serverUrl, cap);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	}

	@Test
	public void testAddContact() {
		driver.findElement(AppiumBy.accessibilityId("Create contact")).click();
		String firstName = "Aaditya";
		String lastName = "Varma";
		String phoneNumber = "999148292";
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='First name']")).sendKeys(firstName);
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Last name']")).sendKeys(lastName);
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='+1']")).sendKeys(phoneNumber);
		// Click Save
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Save']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("large_titleM2")));

		// Assertion
		String savedcontactName = driver.findElement(AppiumBy.id("large_titleM2")).getText();
		Assert.assertEquals(savedcontactName, "Aaditya Varma");

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
