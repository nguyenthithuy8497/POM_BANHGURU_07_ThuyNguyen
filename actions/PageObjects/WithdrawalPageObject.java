package PageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class WithdrawalPageObject extends AbstractPage{
	
	WebDriver driver;

	public WithdrawalPageObject(WebDriver driverMapping) {
		driver= driverMapping;
	}

}
