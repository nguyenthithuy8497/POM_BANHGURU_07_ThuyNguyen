package PageObjects;

import org.openqa.selenium.WebDriver;

import PageUI.HomePageUI;
import PageUI.NewCustomerPageUI;
import commons.AbstractPage;

public class HomePageObject extends AbstractPage {
	WebDriver driver;
	public HomePageObject(WebDriver driverMapping) {
		driver= driverMapping;
	}
	
	public boolean isHomePageDisplayed() {
		waitToElementVisible(driver, HomePageUI.HOMEPAGE_WELCOME_MESSAGE);
		boolean status= isControlDisplayed(driver, HomePageUI.HOMEPAGE_WELCOME_MESSAGE);
		return status;
	}
	public boolean isNewCustomerPageUnDisplay() {
		waitToElementInvisible(driver, NewCustomerPageUI.NEW_CUSTOMER_TEXT);
		return isControlUndisplay(driver, NewCustomerPageUI.NEW_CUSTOMER_TEXT);
	}
	

}
