package activities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity_5 {
	AndroidDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.google.android.apps.messaging");
		options.setAppActivity(".ui.ConversationListActivity");
		options.noReset();

		URL serverUrl = new URI("http://0.0.0.0:4723").toURL();
		// URL serverUrl = new URL("http://127.0.0.1:4723/");

		driver = new AndroidDriver(serverUrl, options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	}

	@Test
	public void sendMessageTest() throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.id("com.google.android.apps.messaging:id/start_chat_fab"))).click();

		// Wait for the actual input field (not the TextView label)
		wait.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"ContactSearchField\"]")))
				.sendKeys("8420930330");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));

		// Type the message
		wait.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.id("com.google.android.apps.messaging:id/compose_message_text")))
				.sendKeys("Hello from Appium");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
