package Selenium_Project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//Goal:​ ​ Verify that the “Directory” menu item is visible and clickable
public class Activity6 {
	WebDriver driver;
	WebDriverWait wait;
	String firstName = "Karthiga";
	String lastName ="Project01";
    
    // login
    @BeforeClass
    public void login() {
        // Initialize Firefox driver
        driver = new FirefoxDriver();
//   	 Navigate to ‘​http://alchemy.hguy.co/orangehrm​’.  
        driver.get("http://alchemy.hguy.co/orangehrm");
      //enter user name
    	driver.findElement(By.id("txtUsername")).sendKeys("orange");
    	//enter password
    	driver.findElement(By.id("txtPassword")).sendKeys("orangepassword123");
    	//click on login 
    	driver.findElement(By.id("btnLogin")).click();
    }
    
//	  Verify that the “Directory” menu item is visible and clickable.
    @Test(priority = 1)
    public void clickonDirectory() {
    	wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_directory_viewDirectory")));
    	
 
    	Assert.assertTrue(driver.findElement(By.id("menu_directory_viewDirectory")).isDisplayed());
    	System.out.println("Directory is visible in the menu");
    	Assert.assertTrue(driver.findElement(By.id("menu_directory_viewDirectory")).isEnabled());
    	System.out.println("Directory is clickable in the menu");
    	driver.findElement(By.id("menu_directory_viewDirectory")).click();
    
    }

//	verify the heading
    @Test(priority = 2)
    public void verifyHeading() {
    	String heading = driver.findElement(By.xpath("//div[@id='content']//h1[text()='Search Directory']")).getText();
    	System.out.println("Page Heading: "+heading);
    	Assert.assertEquals(heading, "Search Directory");
    }
    
// 	close the browser.
    @AfterClass
    public void closeBrowser() {
    	// Close the browser
       driver.quit();
    }
}
