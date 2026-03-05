package Selenium_Project;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

//Goal: Login and retrieve the emergency contacts for the user 
public class Activity9 {
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
    
//	 click on My info in menu
  @Test(priority = 1)
  public void clickonMyInfo() {
  	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
  	wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewMyDetails")));
  	driver.findElement(By.id("menu_pim_viewMyDetails")).click();
  
  }

//	Locate left hand menu and Click on Emergency Contacts
  @Test(priority = 2)
  public void clickOnQualification() {
  	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
     	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='sidenav']//*[text()='Emergency Contacts']")));
  	//click on qualification
  	driver.findElement(By.xpath("//ul[@id='sidenav']//*[text()='Emergency Contacts']")).click();
  }
    
//    get emergency contact details
    @Test(priority = 3)
    public void getEmergencyContactDetails() {
    	WebElement table = driver.findElement(By.id("emgcontact_list"));
    	//get col headers
    	List<WebElement> cols = table.findElements(By.xpath("//*[@id='emgcontact_list']/thead/tr/th"));

    	
    	//get rows
    	List<WebElement> rows = table.findElements(By.xpath("//*[@id='emgcontact_list']/tbody/tr"));
    	System.out.println("Row size:"+rows.size());
    	
    	System.out.println("**********Emergency contact Details*****************");
    	int rowNumber = 1;
    	
    	for(WebElement row:rows) {
    		System.out.println("Contact "+rowNumber);
    		List<WebElement> cells = row.findElements(By.tagName("td"));
	    	for(int i =1; i< cols.size(); i++) {
	    		String headerText = cols.get(i).getText();
	    		String cellText = cells.get(i).getText();
	    		System.out.println(headerText+" : "+cellText);
	    	}
	    	System.out.println("--------------------------------------");
	    	rowNumber++;
    	}
//    
    }
// 	close the browser.
    @AfterClass
    public void closeBroeser() {
    	// Close the browser
      driver.quit();
    }
}
