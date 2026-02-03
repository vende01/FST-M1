package activities;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity_6 extends ActionsBase{

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
		 wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		 
		 driver.get("https://training-support.net/webelements/sliders");
	}
	@Test
	public void method1() {
		
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.SeekBar")));
		
		Dimension dim=driver.manage().window().getSize();
		
		int StartX=(int) (0.50f* dim.getWidth());
		int StartY=(int) (0.72f* dim.getHeight());
		
		Point start=new Point(StartX,StartY);
		
		int endX=(int) (0.33f* dim.getWidth());
		int endY=(int) (0.72f* dim.getHeight());
		
		Point ending=new Point(endX,endY);
		System.out.println(start);
		System.out.println(ending);
		
		doSwipe(driver, start, ending, 2000);
		
		String volumeText = driver
				.findElement(AppiumBy.xpath("//android.view.View/android.widget.TextView[contains(@text, '%')]"))
				.getText();
		
		assertTrue(volumeText.contains("24%"));
	
	}
}
