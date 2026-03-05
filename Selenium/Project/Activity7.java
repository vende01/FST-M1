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

//Goal:​ ​ Add employee qualifications
public class Activity7 {
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
        driver.manage().window().maximize();
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

//	Click on qualificationa
    @Test(priority = 2)
    public void clickOnQualification() {
    	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
       	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='sidenav']//*[text()='Qualifications']")));
    	//click on qualification
    	driver.findElement(By.xpath("//ul[@id='sidenav']//*[text()='Qualifications']")).click();
    }
    
//    add work experience and click on save
    @Test(priority = 3)
    public void addWorkExperience() {
    	wait = new WebDriverWait(driver, Duration.ofSeconds(15));
       	wait.until(ExpectedConditions.elementToBeClickable(By.id("addWorkExperience")));
    	//Click on add
    	driver.findElement(By.id("addWorkExperience")).click();
    	
    	//company
    	driver.findElement(By.id("experience_employer")).sendKeys("IBM");
    	//job title
    	driver.findElement(By.id("experience_jobtitle")).sendKeys("Tester");
    	//comment
    	driver.findElement(By.id("experience_comments")).sendKeys("Selenium Project - Activity7");
    	
    	//click on save 
    	driver.findElement(By.id("btnWorkExpSave")).click();
    }
// 	close the browser.
    @AfterClass
    public void closeBrowser() {
    	// Close the browser
       driver.quit();
    }
}
