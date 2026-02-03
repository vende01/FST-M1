package activities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity_7 {
	AppiumDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void setup() throws MalformedURLException, URISyntaxException {
		//desired capabilities
		UiAutomator2Options caps=new UiAutomator2Options();
		caps.setPlatformName("Android");
		caps.setAutomationName("UiAutomator2");
		caps.setAppPackage("com.android.chrome");
		caps.setAppActivity("com.google.android.apps.chrome.Main");
		//caps.setApp("https://training-support.net");
		caps.noReset();
		
		//appium server url
		URL serverurl=new URI("http://localhost:4723").toURL();
		
		//initialize web driver
		 driver = new AndroidDriver(serverurl, caps);
		 wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 driver.get("https://training-support.net/webelements/lazy-loading");
		
	}
	@Test
	public void method1() {
		ArrayList<WebElement> num_images=new ArrayList<>();
		List<WebElement> imageElements = driver.findElements(AppiumBy.className("android.widget.Image"));

		num_images.addAll(imageElements);
		System.out.println("Before scroll: " + num_images.size());
		
		ArrayList<WebElement> num_images_find=new ArrayList<>();
		 String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
		 List<WebElement> after_scroll	=driver.findElements(AppiumBy.androidUIAutomator(UiScrollable +".scrollIntoView(text(\"This image was lazily loaded!\"))"+".scrollToEnd(50)"));
		 num_images_find.addAll(after_scroll);
		System.out.println("after scroll: " +num_images_find.size());
	}
}
