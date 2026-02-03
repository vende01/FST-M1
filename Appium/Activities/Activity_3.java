package activities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity_3 {
	AppiumDriver driver;
	
	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {
		File apkFile = new File("src/test/resources/Calculator.apk");
		UiAutomator2Options cap = new UiAutomator2Options();
		cap.setPlatformName("Android");
		cap.setAutomationName("UiAutomator2");
		cap.setApp(apkFile.getAbsolutePath());
		cap.noReset();
		
		URL serverUrl = new URI("http://127.0.0.1:4723").toURL();
		driver = new AndroidDriver(serverUrl,cap);
	}

	@Test
	public void additionTest() {
		driver.findElement(AppiumBy.id("digit_5")).click();
		driver.findElement(AppiumBy.accessibilityId("plus")).click();
		driver.findElement(AppiumBy.id("digit_9")).click();
		driver.findElement(AppiumBy.accessibilityId("equals")).click();
		String result = driver.findElement(AppiumBy.id("result_final")).getText();
		System.out.println("5 + 9 = " + result);

		Assert.assertEquals(result,"14");		
	}
	@Test
	public void subtractTest() {
		driver.findElement(AppiumBy.id("digit_1")).click();
        driver.findElement(AppiumBy.id("digit_0")).click();
        driver.findElement(AppiumBy.accessibilityId("minus")).click();
        driver.findElement(AppiumBy.id("digit_5")).click();
        driver.findElement(AppiumBy.accessibilityId("equals")).click();
        String result = driver.findElement(AppiumBy.id("result_final")).getText();
		System.out.println("10 - 5 = "+ result);

        Assert.assertEquals(result, "5");
	}
	@Test
	public void multiplicationTest() {
	    driver.findElement(AppiumBy.accessibilityId("clear")).click();

	    driver.findElement(AppiumBy.id("digit_5")).click();
	    driver.findElement(AppiumBy.accessibilityId("multiply")).click();
	    driver.findElement(AppiumBy.id("digit_1")).click();
	    driver.findElement(AppiumBy.id("digit_0")).click();
	    driver.findElement(AppiumBy.id("digit_0")).click();
	    driver.findElement(AppiumBy.accessibilityId("equals")).click();

	    String result = driver.findElement(AppiumBy.id("result_final")).getText();
	    System.out.println("5 * 100 = " + result);
	    Assert.assertEquals(result, "500");
	}
	@Test
	public void divisionTest() {
	    driver.findElement(AppiumBy.accessibilityId("clear")).click();

	    driver.findElement(AppiumBy.id("digit_5")).click();
	    driver.findElement(AppiumBy.id("digit_0")).click();
	    driver.findElement(AppiumBy.accessibilityId("divide")).click();
	    driver.findElement(AppiumBy.id("digit_2")).click();
	    driver.findElement(AppiumBy.accessibilityId("equals")).click();

	    String result = driver.findElement(AppiumBy.id("result_final")).getText();
	    System.out.println("50 / 2 = " + result);
	    Assert.assertEquals(result, "25");
	}


	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
}
