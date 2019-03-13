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
	public HomePageObject clickToLoginButton() throws Exception{
		waitToElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
		if(driver.toString().toLowerCase().contains("internetexplorer")) {
			clickToElementByJS(driver, LoginPageUI.LOGIN_BUTTON);
			Thread.sleep(3000);
		}
		else {
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		}
		return PageFactoryManager.getHomePage(driver);
	}
	public RegisterPageObject clickToHereLink() throws Exception {
		waitToElementVisible(driver, LoginPageUI.HERE_LINK);
		if(driver.toString().toLowerCase().contains("internetexplorer")) {
			clickToElementByJS(driver, LoginPageUI.HERE_LINK);
			Thread.sleep(3000);
		}
		else {
			clickToElement(driver, LoginPageUI.HERE_LINK);
		}
		return PageFactoryManager.getRegisterPage(driver);
	}
	
	
	
}
