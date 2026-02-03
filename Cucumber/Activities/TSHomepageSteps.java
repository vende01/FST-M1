package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TSHomepageSteps extends BaseClass {
	@Given("user is on the TS homepage")
	public void user_is_on_the_ts_homepage() {
	    driver.get("https://training-support.net");
		assertEquals(driver.getTitle(), "Training Support");
	}

	@When("the user clicks on the About Us link")
	public void the_user_clicks_on_the_about_us_link() {
		driver.findElement(By.linkText("About Us")).click();
	}

	@Then("they are redirected to another page")
	public void they_are_redirected_to_another_page() {
		wait.until(ExpectedConditions.titleIs("About Training Support"));
		assertEquals(driver.getTitle(), "About Training Support");

	}
}
