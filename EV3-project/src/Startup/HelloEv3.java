package Startup;

// import EV3 hardware packages for EV brick finding,
// activating keys and LCD
import lejos.hardware.ev3.EV3;
import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.lcd.TextLCD;
	public class HelloEv3 {
		public static void main(String[] args) {
		// get EV3 brick
		EV3 ev3brick = (EV3) BrickFinder.getLocal();
    	// instantized LCD class for displaying and Keys // class for buttons
    	Keys buttons = ev3brick.getKeys();
    	TextLCD lcddisplay = ev3brick.getTextLCD();
    	// drawing text on the LCD screen based on
    	// coordinates
    	lcddisplay.drawString("Hello Ev3!", 2, 4);
    	// exit program after any button pressed
    	buttons.waitForAnyPress();
	}
}