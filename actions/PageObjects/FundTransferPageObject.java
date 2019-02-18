package PageObjects;

import org.openqa.selenium.WebDriver;

import PageUI.FundTrainsferPageUI;
import commons.AbstractPage;

public class FundTransferPageObject extends AbstractPage{

	WebDriver driver;
	public FundTransferPageObject(WebDriver driverMapping) {
		driver= driverMapping;
	}
	public boolean isFundTransferPageDisplay() {
		waitToElementVisible(driver, FundTrainsferPageUI.FUNDTRAINSFER_TEXT);
		return isControlDisplayed(driver, FundTrainsferPageUI.FUNDTRAINSFER_TEXT);
	}
}
