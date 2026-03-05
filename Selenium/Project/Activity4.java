package Selenium_Project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//Goal:​ ​ Add an employee and their details to the site 
public class Activity4 {
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
    
//	 click on PIm in menu
    @Test(priority = 1)
    public void clickOnPIM() {
    	wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewPimModule")));
    	driver.findElement(By.id("menu_pim_viewPimModule")).click();
    
    }

//	Add the employee
    @Test(priority = 2)
    public void addEmployee() {
    	wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAdd")));
    	//click on add
    	driver.findElement(By.id("btnAdd")).click();
    	
    	//enter first name
    	driver.findElement(By.id("firstName")).clear();
    	driver.findElement(By.id("firstName")).sendKeys(firstName);
    	
    	//enter last name
    	driver.findElement(By.id("lastName")).clear();
    	driver.findElement(By.id("lastName")).sendKeys(lastName);
    	
    	//Click on save
    	driver.findElement(By.id("btnSave")).click();
    }
    
//    Verify added employee
    @Test(priority = 3)
    public void verifyEmployee() {
    	//Click on pim
    	driver.findElement(By.id("menu_pim_viewPimModule")).click();
    	
    	//click on employee list
    	driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='empsearch_employee_name_empName']")));
    	//enter employee name
    	driver.findElement(By.xpath("//input[@id='empsearch_employee_name_empName']")).clear();
    	driver.findElement(By.xpath("//input[@id='empsearch_employee_name_empName']")).sendKeys(firstName+" "+lastName);
    	
    	//click on search 
    	driver.findElement(By.id("searchBtn")).click();
    	
    	//get employee name
    	String actualFN = driver.findElement(By.xpath("//table/tbody/tr/td[3]")).getText();
    	String actualLN = driver.findElement(By.xpath("//table/tbody/tr/td[4]")).getText();
    	
    	//Verify
    	System.out.println("Added Employee:" + actualFN+" "+actualLN);
    	Assert.assertEquals(firstName+" "+lastName, actualFN+" "+actualLN);
    	
    }
// 	close the browser.
    @AfterClass
    public void closeBrowser() {
    	// Close the browser
       driver.quit();
    }
}
