package example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Activity2 {

	public static void main(String[] args) {
		
		
		WebDriver driver = new FirefoxDriver();
		driver.get("https://training-support.net/webelements/login-form/");
		System.out.println(driver.getTitle());
		
		WebElement username = driver.findElement(By.xpath("//*[@id='username']"));
		username.sendKeys("admin");
		
		WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
		password.sendKeys("password");
		
		WebElement submit = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		submit.click();
		
		System.out.println(driver.getTitle());
		driver.quit();

	}

}
