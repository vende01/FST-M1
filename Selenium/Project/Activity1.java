package Selenium_Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//Goal:​ ​ Read the title of the website and verify the text
public class Activity1 {
	 WebDriver driver;
	    
    // Setup function
    @BeforeClass
    public void launchBrowser() {
        // Initialize Firefox driver
        driver = new FirefoxDriver();
//   	 Navigate to ‘​http://alchemy.hguy.co/orangehrm​’.  
        driver.get("http://alchemy.hguy.co/orangehrm");
    }
    
//	 Get the title of the website. 
    @Test(priority = 1)
    public void getPageTitle() {
    	String pageTitle = driver.getTitle();
    	System.out.println("Page Title : "+pageTitle );
    }

//	 Make sure it matches “OrangeHRM” exactly. 
    @Test(priority = 2)
    public void verifyPageTitle() {
        // Assert page title
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
    }
    
//	 close the browser.
    @AfterClass
    public void closeBrowser() {
        // Close the browser
        driver.quit();
    }
}
