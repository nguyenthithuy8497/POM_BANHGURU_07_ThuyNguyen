package PageObjects;

import org.openqa.selenium.WebDriver;

import PageUI.LoginPageUI;
import PageUI.RegisterPageUI;
import commons.AbstractPage;

public class RegisterPageObject extends AbstractPage{
	WebDriver driver;
	public RegisterPageObject(WebDriver driverMapping) {
		driver= driverMapping;
	}
	
	public void intputToEmailIDTextbox(String email) {
		waitToElementVisible(driver, RegisterPageUI.EMAILID_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.EMAILID_TEXTBOX, email);
		
	}
	public void clickToSubmitButton() throws Exception {
		waitToElementVisible(driver, RegisterPageUI.SUBMIT_BUTTON);
		if(driver.toString().toLowerCase().contains("internetexplorer")) {
			clickToElementByJS(driver, RegisterPageUI.SUBMIT_BUTTON);
			Thread.sleep(3000);
		}
		else {
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
		}
		
	}
	public String getUserIDText() {
		waitToElementVisible(driver, RegisterPageUI.USERID_TEXT);
		return getTextElement(driver, RegisterPageUI.USERID_TEXT);
		
	}
	public String getPasswordText() {
		waitToElementVisible(driver, RegisterPageUI.PASSWORD_TEXT);
		return getTextElement(driver, RegisterPageUI.PASSWORD_TEXT);
		
	}
	public LoginPageObject openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);
		return PageFactoryManager.getLoginPage(driver);
	}

}
