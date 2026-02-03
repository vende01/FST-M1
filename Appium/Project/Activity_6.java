package project;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.SupportsContextSwitching;

public class Activity_6 {
	//desired capabilities
	UiAutomator2Options caps;
	URL serverurl;
	AndroidDriver driver1;
	WebDriverWait wait1;
	

	@Test
	public void method4() throws InterruptedException{
		
		caps=new UiAutomator2Options();
		caps.setPlatformName("Android");
		caps.setAutomationName("UiAutomator2");
		caps.setAppPackage("com.android.chrome");
		caps.setAppActivity("com.google.android.apps.chrome.Main");
		caps.setCapability("appium:settings[enableMultiWindows]", true);
		//caps.setCapability("appium:autoAcceptAlerts", true);
		caps.autoGrantPermissions();
		caps.noReset();
		
		
		try {
			serverurl = new URI("http://localhost:4723").toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver1 = new AndroidDriver(serverurl, caps);
		wait1 = new WebDriverWait(driver1, Duration.ofSeconds(8));
		
		
		driver1.get("https://training-support.net/webelements");
		driver1.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"+".scrollIntoView(new UiSelector().textContains(\"Popups\"))"+".scrollToEnd(15)")).click();
		
		wait1.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id='launcher']"))).click();
		
		
		
		Set<String> contexts = driver1.getContextHandles();
		for (String contextName : contexts) {
			System.out.println(contextName);
		    if (contextName.contains("WEBVIEW")) {
		    	
		    	driver1.context(contextName);
		    	String text_switch = driver1.getContext();
		    	System.out.println("Active Element Text: " + text_switch);
		        
		     
		    driver1.switchTo().activeElement().findElement(AppiumBy.xpath("//div[@id='popup']")).click();
          
        driver1.findElement(AppiumBy.xpath("//input[@name='username']")).click();
		    driver1.findElement(AppiumBy.xpath("//input[@name='username']")).sendKeys("admin");
				driver1.findElement(AppiumBy.xpath("//input[@name='password']")).sendKeys("password");
				wait1.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//form[@id='signInForm']/button"))).click();
				break;
		    	
		    }    
		}
		
	  String result2=wait1.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Login Success!']"))).getText();
	  Assert.assertEquals(result2,"Login Success!");
	}	
}

