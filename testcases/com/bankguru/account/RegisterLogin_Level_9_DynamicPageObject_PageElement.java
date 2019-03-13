package com.bankguru.account;

import org.testng.annotations.Test;

import PageObjects.DepositePageObject;
import PageObjects.FundTransferPageObject;
import PageObjects.HomePageObject;
import PageObjects.LoginPageObject;
import PageObjects.NewAccountPageObject;
import PageObjects.NewCustomerPageObject;
import PageObjects.PageFactoryManager;
import PageObjects.RegisterPageObject;
import commons.AbstractPage;
import commons.AbstractTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class RegisterLogin_Level_9_DynamicPageObject_PageElement extends AbstractTest {
	

	
	@Parameters("browser")
	@BeforeClass 
	public void beforeClass(String browserName)  {
		driver = openMultiBrowser(browserName);
		email = "seleniumonline" + randomNumber() + "@gmail.com";
		// Mo url > vao LoginPage
		loginPage= PageFactoryManager.getLoginPage(driver);
		/*   input data*/
		newName = "Automation Test";
		gender="f";
		genderValue="female";
		newDobActual ="01/01/2000";
		newDobExpect="2000-01-01";
		newAddress="123 Address";
		newCity="lao Cai";
		newState="Sa PA";
		newPin="123456";
		newPhoneNumber="0324586698";
		newEmail="newtest"+ randomNumber()+"@gmail.com";
		newPassword="123123";
		
	}
	

	@Test(description="TC01-Register To System")
	public void TC_01_RegisterToSystem() throws Exception {
		
		loginUrl = loginPage.getLoginPageUrl();
		registerPage=loginPage.clickToHereLink();
		registerPage.intputToEmailIDTextbox(email);
		registerPage.clickToSubmitButton();
		
		userID= registerPage.getUserIDText();
		password= registerPage.getPasswordText();
		
	
	}

	@Test(description="TC02-Login to system")
	public void TC_02_LoginWithAboveInformation() throws Exception {
		loginPage = registerPage.openLoginPage(loginUrl);
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickToLoginButton();
		verifyTrue(homePage.isHomePageDisplayed());
		
	}
	
	@Test(description="TC03-Open and check dynamic page display")
	public void TC_03_OpenDynamicAndCheckPageDisplay() {
		newCustomerPage = (NewCustomerPageObject) homePage.openDynamicPage(driver, "New Customer");
		verifyTrue(newCustomerPage.isDynamicPageOrMessageDisplay(driver, "Add New Customer"));
		// NewCustomer > NewAccount 
		newAccountPage = (NewAccountPageObject) newCustomerPage.openDynamicPage(driver, "New Account");

		verifyTrue(newAccountPage.isDynamicPageOrMessageDisplay(driver, "Add new account form"));
		//NewAccount >Deposite
		depositPage = (DepositePageObject) newAccountPage.openDynamicPage(driver, "Deposit");
		verifyTrue(depositPage.isDynamicPageOrMessageDisplay(driver, "Amount Deposit Form"));
		
		//Deposite > FundTransfer
		funTransferPage = (FundTransferPageObject) depositPage.openDynamicPage(driver, "Fund Transfer");
		verifyTrue(funTransferPage.isDynamicPageOrMessageDisplay(driver, "Fund transfer"));
		
		//ex:  FundTransfer > homePage
		
		homePage = (HomePageObject) funTransferPage.openDynamicPage(driver, "Manager");
		Assert.assertTrue(homePage.isHomePageDisplayed());
		//ex:  homePage > NewAccount

		newAccountPage = (NewAccountPageObject) homePage.openDynamicPage(driver, "New Account");
		verifyTrue(newAccountPage.isDynamicPageOrMessageDisplay(driver, "Add new account form"));
		
	}

	@Test(description="TC04-Create new customer")
	public void TC_04_DynamicPageObjectPageElementPageUI() {
		//homePage > NewCustomer
		newCustomerPage = newAccountPage.openNewCustomerPage(driver);
		verifyTrue(newCustomerPage.isDynamicPageOrMessageDisplay(driver, "Add New Customer"));
		
		//dynamic (drive, giá trị động của textbox, giá trị để nhập vào)
		newCustomerPage.inputToDynamicTextbox(driver, "name", newName);
		newCustomerPage.inputToDynamicTextbox(driver, "dob", newDobActual);
		newCustomerPage.inputToDynamicTextbox(driver, "city", newCity);
		newCustomerPage.inputToDynamicTextbox(driver, "state", newState);
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", newPin);
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", newPhoneNumber);
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", newEmail);
		newCustomerPage.inputToDynamicTextbox(driver, "password", newPassword);
		newCustomerPage.clickToDynamicRadioButton(driver, gender);
		newCustomerPage.inputToDynamicTextArea(driver, "addr", newAddress);
		newCustomerPage.clickToDynamicButton(driver, "sub");
		
		verifyTrue(newCustomerPage.isDynamicPageOrMessageDisplay(driver, "Customer Registered Successfully!!!"));
		
		//verify Dynamic Data display after created Customer success
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver,"Customer Name"), newName);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver,"Gender"), genderValue);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver,"Birthdate"), newDobExpect);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver,"Address"), newAddress);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver,"City"), newCity);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver,"State"), newState);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver,"Pin"), newPin);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver,"Mobile No."), newPhoneNumber);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver,"Email"), newEmail);
		
		
		newName = "Automation Test";
		genderValue="female";
		newDobExpect="2000-01-01";
		newAddress="123 Address";
		newCity="lao Cai";
		newState="Sa PA";
		newPin="123456";
		newPhoneNumber="0324586698";
		newEmailExpected="newtest123@gmail.com";
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private String email, userID, password, loginUrl;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;
	private NewCustomerPageObject newCustomerPage;
	private String  newName,gender,genderValue, newDobActual,newDobExpect, newAddress, newCity, newEmailExpected,
	newState, newPin, newPhoneNumber, newEmail, newPassword;
	private String editAddress, editCity, editState, editPin, editPhoneNumber, editEmail;
	private DepositePageObject depositPage;
	private FundTransferPageObject funTransferPage;
	private NewAccountPageObject newAccountPage;
}
