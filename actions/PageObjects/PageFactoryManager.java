package PageObjects;

import org.openqa.selenium.WebDriver;

public class PageFactoryManager {

	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	public static NewCustomerPageObject getNewCustomerPage(WebDriver driver) {
		return new NewCustomerPageObject(driver);
	}
	
	public static NewAccountPageObject getNewAccountPage(WebDriver driver) {
		return new NewAccountPageObject(driver);
	}
	
	public static DepositePageObject getDepositePage(WebDriver driver) {
		return new DepositePageObject(driver);
	}
	
	public static FundTransferPageObject getFunTransferPage(WebDriver driver) {
		return new FundTransferPageObject(driver);
	}
	public static HomePageObject getopenHomePage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new HomePageObject(driver);
	}
	
	public static EditCustomerPageObject getEditCustomerpage(WebDriver driver) {
		return new EditCustomerPageObject(driver);
	}
	
	public static WithdrawalPageObject getWithdrawalPage(WebDriver driver) {
		return new WithdrawalPageObject(driver);
	}
	
	public static BalanceEnquiryPageObject getBalanceEnquiryPage(WebDriver driver) {
		return new BalanceEnquiryPageObject(driver);
	}
	
	public static DeleteCustomerPageObject getDeleteCustomerPage(WebDriver drvier) {
		return new DeleteCustomerPageObject(drvier);
	}
	public static DeleteAccountPageObject getDeleteAccountPage(WebDriver drvier) {
		return new DeleteAccountPageObject(drvier);
	}
	
	
}
