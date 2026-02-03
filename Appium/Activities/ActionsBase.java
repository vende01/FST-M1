package activities;

import static java.time.Duration.ofMillis;

import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;


import java.util.Arrays;


import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;

import org.openqa.selenium.interactions.PointerInput.Kind;

import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.Sequence;


import io.appium.java_client.AppiumDriver;

public class ActionsBase {
	

	//set the pointer type
	private final static PointerInput finger= new PointerInput(Kind.TOUCH, "dummy_name");
	
	//create function to swipe
	public static void doSwipe(AppiumDriver driver,Point start, Point end,int duration) {
		
		
		
		Sequence swipe=new Sequence(finger, 1);//here 1 is min no of actions
		
		swipe.addAction(finger.createPointerMove(ofMillis(0),viewport(),start.getX(),start.getY()));//view port is the area where the touch actions is taking place
		swipe.addAction(finger.createPointerDown(MouseButton.LEFT.asArg()));    //0=LEFT CLICK
		swipe.addAction(new Pause(finger, ofMillis(2000)));
		swipe.addAction(finger.createPointerMove(ofMillis(duration), viewport(),end.getX(),end.getY()));
		swipe.addAction(finger.createPointerUp(MouseButton.LEFT.asArg()));//0=LEFT CLICK
		
		//perform sequence
		driver.perform(Arrays.asList(swipe));
		
	}
	
}
