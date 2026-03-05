package Selenium_Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//Goal:​ ​ Print the url of the header image to the console 
public class Activity2 {
	 WebDriver driver;
	    
	    // Setup function
	    @BeforeClass
	    public void launchBrowser() {
	        // Initialize Firefox driver
	        driver = new FirefoxDriver();
//	   	 Navigate to ‘​http://alchemy.hguy.co/orangehrm​’.  
	        driver.get("http://alchemy.hguy.co/orangehrm");
	    }
	    
//		 Get the url of the website and print to console 
	    @Test
	    public void getPageUrl() {
	    	String pageTitle = driver.getCurrentUrl();
	    	System.out.println("Page URL : "+pageTitle );
	    }
	    
//		close the browser.
	    @AfterClass
	    public void closeBrowser() {
	        // Close the browser
	        driver.quit();
	    }
}
