package project;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity_1 {
	AndroidDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() throws Exception {
		File appFile = new File("src/test/resources/ts-todo-list-v1.apk");

		UiAutomator2Options cap = new UiAutomator2Options();
		cap.setPlatformName("Android");
		cap.setAutomationName("UiAutomator2");
		cap.setApp(appFile.getAbsolutePath());
		cap.noReset();

		URL serverUrl = new URI("http://127.0.0.1:4723").toURL();
		driver = new AndroidDriver(serverUrl, cap);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	@Test
	public void addTasksAndValidate() {
		String[][] tasks = { { "Complete Activity 1", "High" }, { "Complete Activity 2", "Medium" },
				{ "Complete Activity 3", "Low" } };

		for (String[] task : tasks) {
			wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.app.todolist:id/fab_new_task")))
					.click();

			wait.until(
					ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.app.todolist:id/et_new_task_name")))
					.sendKeys(task[0]);
			wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.app.todolist:id/tv_new_task_priority")))
					.click();

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + task[1] + "']")).click();
			driver.findElement(AppiumBy.id("com.app.todolist:id/bt_new_task_ok")).click();
		}

		Assert.assertEquals(driver.findElements(AppiumBy.id("com.app.todolist:id/tv_exlv_task_name")).size(), 3,
				"Tasks were not added correctly");
	}

	

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
