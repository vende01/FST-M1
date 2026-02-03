package project;

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
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import java.util.List;

import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity_4 {
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

	
	@Test
	public void test() {
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.className("android.webkit.WebView")));

		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"+".scrollIntoView(new UiSelector().textContains(\"To-Do List\"))"+".scrollToEnd(100,3)")).click();

		// 1. Find the input field and enter the three tasks
		WebElement todoInput = wait.until(ExpectedConditions
				.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"todo-input\"]")));
		WebElement addButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"todo-add\"]"));

		String[] newTasks = { "Add tasks to list", "Get number of tasks", "Clear the list" };

		for (String task : newTasks) {
			todoInput.sendKeys(task);
			addButton.click();
		}

		// 2. Add assertions to verify the count (2 existing + 3 new = 5)
		List<WebElement> allTasks = driver
				.findElements(AppiumBy.xpath("//android.widget.ListView//android.widget.TextView"));
		int taskCount = allTasks.size();
		System.out.println("Total tasks in list: " + taskCount);

		Assert.assertEquals(taskCount, 5, "Task count mismatch!");

		// 3. Click on each task to strike them out
		List<WebElement> checkBoxes = driver
				.findElements(AppiumBy.xpath("//android.widget.ListView//android.widget.CheckBox"));
		System.out.println("Striking out " + checkBoxes.size() + " tasks...");

		for (WebElement cb : checkBoxes) {
			cb.click();
		}

		// 4. Clear the list
		int totalToDelete = driver.findElements(AppiumBy.xpath("//android.widget.ListView//android.widget.Button"))
				.size();
		System.out.println("Clearing " + totalToDelete + " tasks...");
		for (int i = 0; i < totalToDelete; i++) {
			driver.findElement(AppiumBy.xpath("(//android.widget.ListView//android.widget.Button)[1]")).click();	
		}

	    // 5. Final assertion to verify list is cleared
	    int finalCount = driver.findElements(AppiumBy.xpath("//android.widget.ListView//android.widget.TextView")).size();
	    Assert.assertEquals(finalCount, 0, "List was not cleared successfully!");

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
