package project;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity_3 {
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
	public void testMarkTasksComplete() {
		// Fetch all task's check box
		List<WebElement> tasks = wait.until(driver -> driver.findElements(
				AppiumBy.xpath("(//android.widget.CheckBox[@resource-id=\"com.app.todolist:id/cb_task_done\"])")));

		Assert.assertTrue(tasks.size() >= 3, "Less than 3 tasks present!");

		// 1. Mark first two tasks as complete
		tasks.get(0).click();
		tasks.get(1).click();

		List<WebElement> taskEntries = driver.findElements(AppiumBy.id("com.app.todolist:id/tv_exlv_task_name"));
		WebElement thirdTask = taskEntries.get(2); // third task entry
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence longPress = new Sequence(finger, 1);
		longPress.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),
				thirdTask.getLocation().getX() + thirdTask.getSize().getWidth() / 2,
				thirdTask.getLocation().getY() + thirdTask.getSize().getHeight() / 2));
		longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		longPress.addAction(new org.openqa.selenium.interactions.Pause(finger, Duration.ofSeconds(2)));
		longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(longPress));
		WebElement editOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy
				.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Edit To-Do Task']")));
		editOption.click();
		// Wait and move slider to 50%
		WebElement slider = wait.until(
				ExpectedConditions.elementToBeClickable(AppiumBy.id("com.app.todolist:id/sb_new_task_progress")));
		int sliderWidth = slider.getSize().getWidth();
		int startX = slider.getLocation().getX();
		int yAxis = slider.getLocation().getY() + (slider.getSize().getHeight() / 2);
		int moveToX = startX + (sliderWidth / 2); // 50%
		Sequence moveSlider = new Sequence(finger, 1);
		moveSlider.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, yAxis));
		moveSlider.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		moveSlider.addAction(
				finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), moveToX, yAxis));
		moveSlider.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(moveSlider));
		// Confirm progress by clicking "Okay"
		driver.findElement(AppiumBy.id("com.app.todolist:id/bt_new_task_ok")).click();


        // 3. Open Completed tasks
        driver.findElement(AppiumBy.accessibilityId("More options")).click();
        WebElement completedTasksOption = wait.until(
        	    ExpectedConditions.elementToBeClickable(
        	        AppiumBy.xpath("//android.widget.TextView[@resource-id='com.app.todolist:id/title' and @text='Completed tasks']")
        	    )
        	);
        	completedTasksOption.click();

        // 4. Assertion: Only 2 fully completed tasks visible
        List<WebElement> completedTasks = driver.findElements(AppiumBy.id("com.app.todolist:id/tv_exlv_task_name"));
        Assert.assertEquals(completedTasks.size(), 2, "Number of completed tasks mismatch!");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
