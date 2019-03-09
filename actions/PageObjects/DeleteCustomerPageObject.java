package PageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DeleteCustomerPageObject extends AbstractPage{
	
	WebDriver driver;
	public DeleteCustomerPageObject(WebDriver driverMapping) {
		driver= driverMapping;
	}


}
