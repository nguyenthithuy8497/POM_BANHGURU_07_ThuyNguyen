package PageObjects;

import org.openqa.selenium.WebDriver;

import PageUI.DepositePageUI;
import commons.AbstractPage;

public class DepositePageObject extends AbstractPage{

	WebDriver driver;
	
	public DepositePageObject(WebDriver driverMapping) {
		driver= driverMapping;
	}
	public boolean isDepositPageDisplay() {
		waitToElementVisible(driver, DepositePageUI.DEPOSITE_TEXT);
		return isControlDisplayed(driver, DepositePageUI.DEPOSITE_TEXT);
	}
	
	
}
