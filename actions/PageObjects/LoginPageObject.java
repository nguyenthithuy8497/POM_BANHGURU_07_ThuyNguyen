package PageObjects;

import org.openqa.selenium.WebDriver;

import PageUI.LoginPageUI;
import commons.AbstractPage;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;
	//driver chưa có dữ liệu do chưa được khởi tạo
	//hàm khởi tạo Constructor
	public LoginPageObject(WebDriver driverMapping) {
		driver = driverMapping;
		
	}

	public String getLoginPageUrl() {
		
		return	getCurrentUrl(driver);
	}
	
	
	public void inputToUserIDTextbox(String userID) {
		waitToElementVisible(driver, LoginPageUI.USERID_TEXTBOX);
		senkeyToElement(driver, LoginPageUI.USERID_TEXTBOX, userID);
		
	}
	public void inputToPasswordTextbox(String password) {
		waitToElementVisible(driver, LoginPageUI.PASS_TEXTBOX);
		senkeyToElement(driver, LoginPageUI.PASS_TEXTBOX, password);
		
	}
	public HomePageObject clickToLoginButton() {
		waitToElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageFactoryManager.getHomePage(driver);
	}
	public RegisterPageObject clickToHereLink() {
		waitToElementVisible(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
		return PageFactoryManager.getRegisterPage(driver);
	}
	
	
	
}
