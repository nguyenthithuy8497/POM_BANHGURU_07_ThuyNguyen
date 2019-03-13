package com.bankguru.payment;

import org.testng.annotations.Test;

import com.bankguru.account.RegisterLogin_Global_Register;

import PageObjects.BalanceEnquiryPageObject;
import PageObjects.DeleteAccountPageObject;
import PageObjects.DeleteCustomerPageObject;
import PageObjects.DepositePageObject;
import PageObjects.EditCustomerPageObject;
import PageObjects.FundTransferPageObject;
import PageObjects.HomePageObject;
import PageObjects.LoginPageObject;
import PageObjects.NewAccountPageObject;
import PageObjects.NewCustomerPageObject;
import PageObjects.PageFactoryManager;
import PageObjects.RegisterPageObject;
import PageObjects.WithdrawalPageObject;
import PageUI.HomePageUI;
import PageUI.NewAccounPageUI;
import commons.AbstractTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.List;

import javax.swing.plaf.basic.BasicLabelUI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Payment01_NewCustomerAccount extends AbstractTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) throws Exception {
		driver = openMultiBrowser(browserName);
		loginPage = PageFactoryManager.getLoginPage(driver);
		loginPage.inputToUserIDTextbox(RegisterLogin_Global_Register.USER_ID);
		loginPage.inputToPasswordTextbox(RegisterLogin_Global_Register.PASSWORD);
		homePage = loginPage.clickToLoginButton();
		
		Assert.assertTrue(homePage.isHomePageDisplayed());

		/* input data */
		newName = "Automation Test";
		gender = "f";
		genderValue = "female";
		newDob = "01-01-2000";
		newDobValue = "2000-01-01";
		newAddress = "123 Address";
		newCity = "lao Cai";
		newState = "Sa PA";
		newPin = "123456";
		newPhoneNumber = "0324586698";
		newEmail = "newtest" + randomNumber() + "@gmail.com";
		newPassword = "123123";

		editAddress = "234 Edit Address";
		editCity = "Vinh";
		editState = "Nghe An";
		editPin = "654321";
		editPhoneNumber = "0357953217";
		editEmail = "edittest" + randomNumber() + "@gmail.com";
		
	}

	@Test
	public void TC_01_CreateNewCustomer() {
		System.out.println("TestCase_01-step_01: open new customer pgae");
		newCustomerPage = (NewCustomerPageObject) homePage.openDynamicPage(driver, "New Customer");
		verifyTrue(newCustomerPage.isDynamicPageOrMessageDisplay(driver, "Add New Customer"));

		System.out.println("TestCase_01-step_02: input data");
		newCustomerPage.inputToDynamicTextbox(driver, "name", newName);
		//newCustomerPage.inputToDynamicTextbox(driver, "dob", newDob);
		newCustomerPage.inputToDynamicTextbox(driver, "city", newCity);
		newCustomerPage.inputToDynamicTextbox(driver, "state", newState);
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", newPin);
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", newPhoneNumber);
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", newEmail);
		newCustomerPage.inputToDynamicTextbox(driver, "password", newPassword);
		newCustomerPage.inputToDynamicTextArea(driver, "addr", newAddress);
		newCustomerPage.clickToDynamicRadioButton(driver, gender);	
		newCustomerPage.inputDateTextbox(driver, "dob", newDob);
		newCustomerPage.clickToDynamicButton(driver, "sub");
		

		System.out.println("TestCase_01-step_03: verify customer register success");
		verifyTrue(newCustomerPage.isDynamicPageOrMessageDisplay(driver, "Customer Registered Successfully!!!"));

		System.out.println("TestCase_01-step_04: verify output data equal input data");
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "Customer Name"), newName);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "Gender"), genderValue);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "Birthdate"), newDobValue);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "Address"), newAddress);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "City"), newCity);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "State"), newState);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "Pin"), newPin);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "Mobile No."), newPhoneNumber);
		verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "Email"), newEmail);
		
	}

	@Test
	public void TC_02_EditCustomerAccount() {
		System.out.println("TestCase_02-Step_01: get customer ID");
		customerID = newCustomerPage.getDynamicTextInTable(driver, "Customer ID");

		System.out.println("TestCase_02-Step_02: OPen edit page");
		newCustomerPage.openDynamicMorePage(driver, "Edit Customer");
		editCustomerPage = PageFactoryManager.getEditCustomerpage(driver);

		System.out.println("TestCase_02-Step_03: verify edit customer form display");
		verifyTrue(editCustomerPage.isDynamicPageOrMessageDisplay(driver, "Edit Customer Form"));

		System.out.println("TestCase_02-Step_04: input customerID to fiel");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", customerID);

		System.out.println("TestCase_02-Step_05: click submit");
		editCustomerPage.clickToDynamicButton(driver, "AccSubmit");

		System.out.println("TestCase_02-Step_06: verify eidt customer page display");
		verifyTrue(editCustomerPage.isDynamicPageOrMessageDisplay(driver, "Edit Customer"));
		

		System.out.println("TestCase_02-Step_07: edit data");
		editCustomerPage.inputToDynamicTextbox(driver, "city", editCity);
		editCustomerPage.inputToDynamicTextbox(driver, "state", editState);
		editCustomerPage.inputToDynamicTextbox(driver, "pinno", editPin);
		editCustomerPage.inputToDynamicTextbox(driver, "telephoneno", editPhoneNumber);
		editCustomerPage.inputToDynamicTextbox(driver, "emailid", editEmail);
		editCustomerPage.inputToDynamicTextArea(driver, "addr", editAddress);
		
		editCustomerPage.clickToDynamicButton(driver, "sub");
		
		verifyTrue(editCustomerPage.isDynamicPageOrMessageDisplay(driver, "Customer details updated Successfully!!!"));
	}
	@Test
	public void TC_03_AddNewccount() throws Exception {
		System.out.println("TestCase_03-Step_01: open new account");
		editCustomerPage.openDynamicMorePage(driver, "New Account");
		newAccountPage = PageFactoryManager.getNewAccountPage(driver);
		
		System.out.println("TestCase_03-Step_02: verify Add account form display");
		verifyTrue(newAccountPage.isDynamicPageOrMessageDisplay(driver, "Add new account form"));
		
		System.out.println("TestCase_03-Step_03: add data");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", customerID);
		newAccountPage.clickToElement(driver, NewAccounPageUI.NEW_ACCOUNT_SELECT);
		newAccountPage.hoverMouseToElement(driver, NewAccounPageUI.NEW_ACCOUNT_OPTION);
		newAccountPage.clickToElement(driver, NewAccounPageUI.NEW_ACCOUNT_OPTION);
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", "50000");
		newAccountPage.clickToDynamicButton(driver, "button2");
		
		System.out.println("TestCase_03-Step_04: verify message display ");
		verifyTrue(newAccountPage.isDynamicPageOrMessageDisplay(driver, "Account Generated Successfully!!!"));
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Current Amount"), "50000");
	}
	
	@Test
	public void TC_04_TransferMoneyIntoCurrentAccount() {
		System.out.println("TestCase_04-Step_01: get account ID tu new account page");
		accountNo = newAccountPage.getDynamicTextInTable(driver, "Account ID");
		
		System.out.println("TestCase_04-Step_02: open deposit page");
		newAccountPage.openDynamicMorePage(driver, "Deposit");
		depositPage= PageFactoryManager.getDepositePage(driver);
		
		System.out.println("TestCase_04-Step_03: verify Deposit Form display");
		verifyTrue(depositPage.isDynamicPageOrMessageDisplay(driver, "Amount Deposit Form"));
		
		System.out.println("TestCase_04-Step_04: Input data");
		depositPage.inputToDynamicTextbox(driver, "accountno", accountNo);
		depositPage.inputToDynamicTextbox(driver, "ammount", "5000");
		depositPage.inputToDynamicTextbox(driver, "desc", "Deposite");
		depositPage.clickToDynamicButton(driver, "AccSubmit");
		
		verifyTrue(depositPage.isDynamicPageOrMessageDisplay(driver, "Transaction details of Deposit for Account "+accountNo));
		verifyEquals(depositPage.getDynamicTextInTable(driver, "Current Balance"), "55000");
	}
	
	@Test
	public void TC_05_WithdrowMoneyFromCurrentAccount() {
		System.out.println("TestCase_05-Step_01: open Withdrow page");
		depositPage.openDynamicMorePage(driver, "Withdrawal");
		withdrawalPage= PageFactoryManager.getWithdrawalPage(driver);
		
		System.out.println("TestCase_05-Step_02: verify Withdrawal display");
		verifyTrue(withdrawalPage.isDynamicPageOrMessageDisplay(driver, "Amount Withdrawal Form"));
		
		System.out.println("TestCase_05-Step_03: input data");
		withdrawalPage.inputToDynamicTextbox(driver, "accountno", accountNo);
		withdrawalPage.inputToDynamicTextbox(driver, "ammount", "15000");
		withdrawalPage.inputToDynamicTextbox(driver, "desc", "Withdraw");
		withdrawalPage.clickToDynamicButton(driver, "AccSubmit");
		
		System.out.println("TestCase_05-Step_04: verify message display and Current Amount= 40000");
		verifyTrue(withdrawalPage.isDynamicPageOrMessageDisplay(driver, "Transaction details of Withdrawal for Account "+accountNo));
		verifyEquals(withdrawalPage.getDynamicTextInTable(driver, "Current Balance"), "40000");
		
	}
	
	@Test
	public void TC_06_TransferMoneyIntoAnotherAccount() {
		System.out.println("TestCase_06-Step_01: Open Fund Transfer Page");
		withdrawalPage.openDynamicMorePage(driver, "Fund Transfer");
		fundTransferPage= PageFactoryManager.getFunTransferPage(driver);
		
		System.out.println("TestCase_06-Step_02: verify Fund transfer display");
		verifyTrue(fundTransferPage.isDynamicPageOrMessageDisplay(driver, "Fund transfer"));
		
		System.out.println("TestCase_06-Step_03: Input data");
		fundTransferPage.inputToDynamicTextbox(driver, "payersaccount", accountNo);
		fundTransferPage.inputToDynamicTextbox(driver, "payeeaccount", "57387");
		fundTransferPage.inputToDynamicTextbox(driver, "ammount", "10000");
		fundTransferPage.inputToDynamicTextbox(driver, "desc", "Transfer");
		fundTransferPage.clickToDynamicButton(driver, "AccSubmit");
		
		System.out.println("TestCase_06-Step_04: verify Fund transfer success");
		verifyTrue(fundTransferPage.isDynamicPageOrMessageDisplay(driver, "Fund Transfer Details"));
		verifyEquals(fundTransferPage.getDynamicTextInTable(driver, "Amount"), "10000");
		
	}
	@Test
	public void TC_07_CheckCurrentAccountBalance() {
		System.out.println("TestCase_07-Step_01: open Balance Enquiry");
		fundTransferPage.openDynamicMorePage(driver, "Balance Enquiry");
		balanceEnquiryPage = PageFactoryManager.getBalanceEnquiryPage(driver);
		
		System.out.println("TestCase_07-Step_02: verify Balance Enquiry display");
		verifyTrue(balanceEnquiryPage.isDynamicPageOrMessageDisplay(driver, "Balance Enquiry Form"));
		
		System.out.println("TestCase_07-Step_03: input data to  Balance Enquiry");
		balanceEnquiryPage.inputToDynamicTextbox(driver, "accountno", accountNo);
		balanceEnquiryPage.clickToDynamicButton(driver, "AccSubmit");
		
		System.out.println("TestCase_07-Step_04: verify Balance detail successly");
		verifyTrue(balanceEnquiryPage.isDynamicPageOrMessageDisplay(driver, "Balance Details for Account "+accountNo));
		verifyEquals(balanceEnquiryPage.getDynamicTextInTable(driver, "Balance"), "30000");

	}
	
	@Test
	public void TC_08_DeleteAllAccountOfCustomer() throws Exception{
		System.out.println("TestCase_08-Step_01: Open Delete Account Page");
		balanceEnquiryPage.openDynamicMorePage(driver, "Delete Account");
		deleteAccountPage= PageFactoryManager.getDeleteAccountPage(driver);
		
		System.out.println("TestCase_08-Step_02:verify Delete Account Page dislay");
		verifyTrue(deleteAccountPage.isDynamicPageOrMessageDisplay(driver, "Delete Account Form"));
		
		System.out.println("TestCase_08-Step_03: Input Data");
		deleteAccountPage.inputToDynamicTextbox(driver, "accountno", accountNo);
		deleteAccountPage.clickToDynamicButton(driver, "AccSubmit");
		
		System.out.println("TestCase_08-Step_04: Accept Alert");
		Thread.sleep(2000);
		verifyEquals(deleteAccountPage.getTextAlert(driver), "Do you really want to delete this Account?");
		deleteAccountPage.acceptAlert(driver);
		Thread.sleep(2000);
		verifyEquals(deleteAccountPage.getTextAlert(driver), "Account Deleted Sucessfully");
		Thread.sleep(2000);
		deleteAccountPage.acceptAlert(driver);
		Thread.sleep(2000);
	}
	
	@Test
	public void TC_09_DeleteAllAccountOfCustomer() throws Exception{
		System.out.println("TestCase_09-Step_01: Open Delete Customer Page");
		homePage.openDynamicMorePage(driver, "Delete Customer");
		deleteCustomerPage= PageFactoryManager.getDeleteCustomerPage(driver);
		
		System.out.println("TestCase_09-Step_02: verify Delete Customer display");
		verifyTrue(deleteCustomerPage.isDynamicPageOrMessageDisplay(driver, "Delete Customer Form"));
		
		System.out.println("TestCase_09-Step_03: Input Data");
		deleteCustomerPage.inputToDynamicTextbox(driver, "cusid", customerID);
		deleteCustomerPage.clickToDynamicButton(driver, "AccSubmit");
		
		System.out.println("TestCase_09-Step_04: Accept alert");
		Thread.sleep(2000);
		deleteCustomerPage.acceptAlert(driver);
		Thread.sleep(2000);
		verifyEquals(deleteCustomerPage.getTextAlert(driver), "Customer deleted Successfully");
		deleteCustomerPage.acceptAlert(driver);
	
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	private WebDriver driver;
	private String 	newName, gender, genderValue,
					newDob, newDobValue, newAddress, newCity, newState, newPin,
					newPhoneNumber, newEmail, newPassword;
	private String editAddress, editCity, editState, editPin, editPhoneNumber, editEmail;
	private String customerID, accountNo; 
	private LoginPageObject loginPage;
	private HomePageObject homePage;
	private NewCustomerPageObject newCustomerPage;
	private EditCustomerPageObject editCustomerPage;
	private NewAccountPageObject newAccountPage;
	private DepositePageObject depositPage;
	private WithdrawalPageObject withdrawalPage;
	private FundTransferPageObject fundTransferPage;
	private BalanceEnquiryPageObject balanceEnquiryPage;
	private DeleteCustomerPageObject deleteCustomerPage;
	private DeleteAccountPageObject deleteAccountPage;
	
	
}
