package stepDefinitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseClass {
	@Given(": the user is on the login page")
	public void the_user_is_on_the_login_page() {
		// Write code here that turns the phrase above into concrete actions
		driver.get("https://training-support.net/webelements/login-form");
	}

	@When(": the user enters username and password")
	public void the_user_enters_username_and_password() {
		WebElement userName = driver.findElement(By.xpath("//input[@id='username']"));
		userName.sendKeys("admin");
		WebElement userPassword = driver.findElement(By.xpath("//input[@id='password']"));
		userPassword.sendKeys("password");
	}

	@When(": the user enters {string} and {string}")
	public void enterCredentialsFromInputs(String username, String password) {
		// Find the input fields
		WebElement usernameElement = driver.findElement(By.id("username"));
		WebElement passwordElement = driver.findElement(By.id("password"));
		// Clear the fields
		usernameElement.clear();
		passwordElement.clear();

		usernameElement.sendKeys(username);
		passwordElement.sendKeys(password);
	}

	@And(": clicks the submit button")
	public void clicks_the_submit_button() {
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
	}

	@Then(": get the confirmation message and verify it")
	public void get_the_confirmation_message_and_verify_it() {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h2.mt-5"), "Welcome"));
		String confirmationMessage = driver.findElement(By.cssSelector(".mt-5.text-center")).getText();
		Assertions.assertEquals("Welcome Back, Admin!", confirmationMessage);
	}

	@Then(": get the confirmation text and verify message as {string}")
	public void confirmMessageAsInput(String expectedMessage) {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h2.mt-5"), "Welcome"));
		String confirmationMessage = driver.findElement(By.cssSelector(".mt-5.text-center")).getText();
		Assertions.assertEquals(expectedMessage, confirmationMessage);
	}
}
