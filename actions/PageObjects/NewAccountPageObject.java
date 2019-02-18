package PageObjects;

import org.openqa.selenium.WebDriver;

import PageUI.NewAccounPageUI;
import PageUI.NewCustomerPageUI;
import commons.AbstractPage;

public class NewAccountPageObject extends AbstractPage{
	WebDriver driver;
	public NewAccountPageObject(WebDriver driverMapping) {
		driver= driverMapping;
	}
	public boolean isNewAccountPageDisplay() {
		waitToElementVisible(driver, NewAccounPageUI.NEW_ACCOUNT_TEXT);
		return isControlDisplayed(driver, NewAccounPageUI.NEW_ACCOUNT_TEXT);

	}
	
	

}
