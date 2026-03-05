package Selenium_Project;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

// Applying for a leave 
public class Activity8 {
	WebDriver driver;
	WebDriverWait wait;

    
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
    
//	 click on apply leave
    @Test(priority = 1)
    public void clickOnApplyLeave() {
    	driver.findElement(By.id("menu_dashboard_index")).click();
    	
    	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='quickLaunge']//a[@href='/orangehrm/symfony/web/index.php/leave/applyLeave']")));
    	driver.findElement(By.xpath("//*[@class='quickLaunge']//a[@href='/orangehrm/symfony/web/index.php/leave/applyLeave']")).click();
    
    }

//	Enter leave details and apply leave
    @Test(priority = 2)
    public void applyLeave() {
    	
    	//leave type
    	Select leaveType = new Select(driver.findElement(By.id("applyleave_txtLeaveType")));
    	leaveType.selectByVisibleText("DayOff");
    	
    	//from date
    	driver.findElement(By.id("applyleave_txtFromDate")).click();
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");

        // Get today's date and add days
        LocalDate today = LocalDate.now();
        LocalDate fromDate = today.plusDays(5);
        LocalDate toDate = today.plusDays(7);
        // Format the date as a string
        String fromDateStr = fromDate.format(formatter);

        // Print the result
        System.out.println("From date: " + fromDateStr);
        // Format the date as a string
        String toDateStr = toDate.format(formatter);

        // Print the result
        System.out.println("To date: " + toDateStr);
        
    	String[] leaveFrom = fromDateStr.split("-");
  	  	String fromDay = leaveFrom[2];
        String fromMonth = leaveFrom[1];
        String fromYear = leaveFrom[0];
  	
            driver.findElement(By.className("ui-datepicker-month")).click();
            WebElement monthElement = driver.findElement(By.className("ui-datepicker-month"));
            Select month1 = new Select(monthElement);
            month1.selectByVisibleText(fromMonth);
            
            driver.findElement(By.className("ui-datepicker-year")).click();
            Select year1 = new Select(driver.findElement(By.className("ui-datepicker-year")));
            year1.selectByVisibleText(fromYear);

        // Select the day
        driver.findElement(By.xpath("//table/tbody/tr/td/a[text()='"+fromDay+"']")).click();
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ui-datepicker-month")));
        
    	driver.findElement(By.xpath("//label[@for='applyleave_txtToDate']")).click();
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='applyleave_txtToDate']")));
        //To date
        driver.findElement(By.xpath("//*[@id='applyleave_txtToDate']")).click();
        
        String[] leaveTo = toDateStr.split("-");
  	  	String toDay = leaveTo[2];
        String toMonth = leaveTo[1];
        String toYear = leaveTo[0];
  	
            driver.findElement(By.className("ui-datepicker-month")).click();
            WebElement monthElement1 = driver.findElement(By.className("ui-datepicker-month"));
            Select monthTo = new Select(monthElement1);
            monthTo.selectByVisibleText(toMonth);
            
            driver.findElement(By.className("ui-datepicker-year")).click();
            Select yearTo = new Select(driver.findElement(By.className("ui-datepicker-year")));
            yearTo.selectByVisibleText(toYear);

        // Select the day
        driver.findElement(By.xpath("//table/tbody/tr/td/a[text()='"+toDay+"']")).click();
    	
        //Enter comment
        driver.findElement(By.id("applyleave_txtComment")).sendKeys("Personal leave");
        //click on apply
        driver.findElement(By.id("applyBtn")).click();
  }
    
// 	close the browser.
    @AfterClass
    public void closeBroeser() {
    	// Close the browser
       driver.quit();
    }
}
