package Selenium_Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//Goal:​ ​ Open the site and login with the credentials provided 
public class Activity3 {
	 WebDriver driver;
	    
	    // Setup function
	    @BeforeClass
	    public void launchBrowser() {
	        // Initialize Firefox driver
	        driver = new FirefoxDriver();
//	   	 Navigate to ‘​http://alchemy.hguy.co/orangehrm​’.  
	        driver.get("http://alchemy.hguy.co/orangehrm");
	    }
	    
//		 Enter User name, password and click on login
	    @Test(priority = 1)
	    public void login() {
	    	//enter user name
	    	driver.findElement(By.id("txtUsername")).sendKeys("orange");
	    	//enter password
	    	driver.findElement(By.id("txtPassword")).sendKeys("orangepassword123");
	    	//click on login 
	    	driver.findElement(By.id("btnLogin")).click();
	    }

//		 Verify the home page is opened
	    @Test(priority = 2)
	    public void verifyHomePage() {
	        // Assert page title
	    	System.out.println("Homepage URL:" + driver.getCurrentUrl());
	        Assert.assertEquals(driver.getCurrentUrl(), "http://alchemy.hguy.co:8080/orangehrm/symfony/web/index.php/dashboard");
	    }
	    
//	 	close the browser.
	    @AfterClass
	    public void closeBrowser() {
	        // Close the browser
	        driver.quit();
	    }
}
