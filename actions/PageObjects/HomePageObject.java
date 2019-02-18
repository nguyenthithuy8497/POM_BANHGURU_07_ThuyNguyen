package PageObjects;

import org.openqa.selenium.WebDriver;

import PageUI.HomePageUI;
import commons.AbstractPage;

public class HomePageObject extends AbstractPage {
	WebDriver driver;
	public HomePageObject(WebDriver driverMapping) {
		driver= driverMapping;
	}
	
	public boolean isHomePageDisplayed() {
		waitToElementVisible(driver, HomePageUI.HOMEPAGE_WELCOME_MESSAGE);
		return isControlDisplayed(driver, HomePageUI.HOMEPAGE_WELCOME_MESSAGE);
	}

	

}
