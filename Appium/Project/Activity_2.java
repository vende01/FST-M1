package project;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class Activity_2 {
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
	public void testDeadline() {

		List<WebElement> tasks = wait
				.until(driver -> driver.findElements(AppiumBy.id("com.app.todolist:id/tv_exlv_task_name")));

		Assert.assertTrue(tasks.size() > 0, "No tasks found to edit");
		WebElement firstTask = tasks.get(0);

		// Create a finger pointer input
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Define a long press sequence
		Sequence longPress = new Sequence(finger, 1);
		longPress.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),
				firstTask.getLocation().getX() + firstTask.getSize().getWidth() / 2,
				firstTask.getLocation().getY() + firstTask.getSize().getHeight() / 2));
		longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		longPress.addAction(new org.openqa.selenium.interactions.Pause(finger, Duration.ofSeconds(2)));
		longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the action
		driver.perform(Arrays.asList(longPress));
		WebElement editOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy
				.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Edit To-Do Task']")));
		editOption.click();
		WebElement deadlineField = wait.until(
				ExpectedConditions.elementToBeClickable(AppiumBy.id("com.app.todolist:id/tv_todo_list_deadline")));
		deadlineField.click();
		LocalDate today = LocalDate.now();
		LocalDate nextSaturday = today.with(DayOfWeek.SATURDAY);
		if (!today.isBefore(nextSaturday)) {
			nextSaturday = nextSaturday.plusWeeks(1);
		}

		// Formating the date exactly like content-desc in the calendar
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
		String targetDateDesc = nextSaturday.format(formatter);
		WebElement nextBtn = driver.findElement(AppiumBy.id("android:id/next"));

		//Navigate months until the day is visible
		while (driver.findElements(AppiumBy.xpath("//android.view.View[@content-desc='" + targetDateDesc + "']"))
				.isEmpty()) {
			nextBtn.click();
		}

		// 5. Click the target day
		WebElement targetDay = driver
				.findElement(AppiumBy.xpath("//android.view.View[@content-desc='" + targetDateDesc + "']"));
		targetDay.click();
		
		//Fetching the chosen deadline date for assertion
		String deadlineDate = driver.findElement(AppiumBy.id("android:id/date_picker_header_date")).getText();
		// Confirm deadline by clicking "Okay"
		driver.findElement(AppiumBy.id("com.app.todolist:id/bt_deadline_ok")).click();
		// Again click "Okay to save changes"
		driver.findElement(AppiumBy.id("com.app.todolist:id/bt_new_task_ok")).click();
		System.out.println(deadlineDate);

		DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("EEE, MMM d", Locale.ENGLISH);
		String expectedDate = nextSaturday.format(displayFormatter); // "Sat, Jan 6"

		// Assertion
		Assert.assertEquals(deadlineDate, expectedDate,
				"Deadline was not set correctly! Expected: " + expectedDate + ", Found: " + deadlineDate);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
