package PageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import PageUI.HomePageUI;
import PageUI.LoginPageUI;
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
	public boolean isHomePageUnDispalay() {
		waitToElementInvisible(driver, HomePageUI.HOMEPAGE_WELCOME_MESSAGE);
		return isControlUndisplay(driver, HomePageUI.HOMEPAGE_WELCOME_MESSAGE);
	}
	
	public boolean isAddCustomerFormUnDisplayed() {
		waitToElementInvisible(driver,NewCustomerPageUI.NEW_CUSTOMER_FORM);
		return isControlUndisplay(driver, NewCustomerPageUI.NEW_CUSTOMER_FORM);
	}
	
	

}
