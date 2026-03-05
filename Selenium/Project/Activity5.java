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

//Goal:​ ​ Edit a user’s information
public class Activity5 {
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
    	wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewMyDetails")));
    	driver.findElement(By.id("menu_pim_viewMyDetails")).click();
    
    }

//	Add the employee
    @Test(priority = 2)
    public void clickonEdit() {
    	wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));
    	//click on edit
    	driver.findElement(By.id("btnSave")).click();
 
    }
    
//   Edit details
    @Test(priority = 3)
    public void editDetails() {
  
    	//enter First name
    	driver.findElement(By.id("personal_txtEmpFirstName")).clear();
    	driver.findElement(By.id("personal_txtEmpFirstName")).sendKeys("Karthiga");
    	// second name
    	driver.findElement(By.id("personal_txtEmpMiddleName")).clear();
    	//last name
    	driver.findElement(By.id("personal_txtEmpLastName")).clear();
    	driver.findElement(By.id("personal_txtEmpLastName")).sendKeys("N");
    	//gender
    	driver.findElement(By.id("personal_optGender_2")).click();
    	//nationality
    	Select nationality = new Select(driver.findElement(By.id("personal_cmbNation")));
    	nationality.selectByVisibleText("French");
    	//DOB
    	driver.findElement(By.id("personal_DOB")).click();
    	
    	  String day = "2";
          String month = "Jun";
          String year = "1998";
    	
              driver.findElement(By.className("ui-datepicker-month")).click();
              WebElement monthElement = driver.findElement(By.className("ui-datepicker-month"));
              Select month1 = new Select(monthElement);
              month1.selectByVisibleText(month);
              
              driver.findElement(By.className("ui-datepicker-year")).click();
              Select year1 = new Select(driver.findElement(By.className("ui-datepicker-year")));
              year1.selectByVisibleText(year);

          // Select the day
          driver.findElement(By.xpath("//table/tbody/tr/td/a[text()='"+day+"']")).click();
    }
    
    @Test(priority = 4)
    public void clickonSave() {
    	driver.findElement(By.id("btnSave")).click();
    }
// 	close the browser.
    @AfterClass
    public void closeBrowser() {
    // Close the browser
       driver.quit();
    }
}
