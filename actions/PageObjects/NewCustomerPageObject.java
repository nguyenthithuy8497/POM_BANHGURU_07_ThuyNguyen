package PageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import PageUI.NewCustomerPageUI;
import commons.AbstractPage;

public class NewCustomerPageObject extends AbstractPage{
	WebDriver driver;
	public NewCustomerPageObject(WebDriver driverMapping) {
		driver= driverMapping;
	}
	
	public boolean isNewCustomerPageDispalay() {
		waitToElementVisible(driver, NewCustomerPageUI.NEW_CUSTOMER_TEXT);
		
		return isControlDisplayed(driver, NewCustomerPageUI.NEW_CUSTOMER_TEXT);
	}
	

}
