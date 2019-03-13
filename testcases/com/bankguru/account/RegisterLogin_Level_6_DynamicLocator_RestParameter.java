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

public class RegisterLogin_Level_6_DynamicLocator_RestParameter extends AbstractTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private HomePageObject homePage;
	private NewCustomerPageObject newCustomerPage;
	private NewAccountPageObject newAccountPage;
	private DepositePageObject depositPage;
	private FundTransferPageObject funTransferPage;
	
	@Parameters("browser")
	@BeforeClass 
	public void beforeClass(String browserName) throws Exception  {
		driver = openMultiBrowser(browserName);
		
		// Mo url > vao LoginPage
		loginPage= PageFactoryManager.getLoginPage(driver);
		loginPage.inputToUserIDTextbox(RegisterLogin_Global_Register.USER_ID);
		loginPage.inputToPasswordTextbox(RegisterLogin_Global_Register.PASSWORD);
		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isHomePageDisplayed());
		
	}
	

	@Test
	public void TC_01_WebDriverLifeCycle() {
		
		// số lượng page ít (vài chục)
		newCustomerPage = (NewCustomerPageObject) homePage.openDynamicPage(driver, "New Customer");
		
	/*	//số lượng pgae nhiều (trăm ngàn page)
		homePage.openDynamicMorePage(driver, "New Customer");
		newCustomerPage = PageFactoryManager.getNewCustomerPage(driver);
		
		*/		
		Assert.assertTrue(newCustomerPage.isNewCustomerPageDispalay());
		// NewCustomer > NewAccount 
		newAccountPage = (NewAccountPageObject) newCustomerPage.openDynamicPage(driver, "New Account");

		Assert.assertTrue(newAccountPage.isNewAccountPageDisplay());
		//NewAccount >Deposite
		depositPage = (DepositePageObject) newAccountPage.openDynamicPage(driver, "Deposit");
		Assert.assertTrue(depositPage.isDepositPageDisplay());
		
		//Deposite > FundTransfer
		funTransferPage = (FundTransferPageObject) depositPage.openDynamicPage(driver, "Fund Transfer");
		Assert.assertTrue(funTransferPage.isFundTransferPageDisplay());
		
		//ex:  FundTransfer > homePage
		
		homePage = (HomePageObject) funTransferPage.openDynamicPage(driver, "Manager");
		Assert.assertTrue(homePage.isHomePageDisplayed());
		//ex:  homePage > NewAccount

		newAccountPage = (NewAccountPageObject) homePage.openDynamicPage(driver, "New Account");
		Assert.assertTrue(newAccountPage.isNewAccountPageDisplay());
		
		//ex:   NewAccount > NewCustomer kiểu nhiều page( trăm nghìn page)
		newAccountPage.openDynamicMorePage(driver, "New Customer");
		newCustomerPage = PageFactoryManager.getNewCustomerPage(driver);
		Assert.assertTrue(newCustomerPage.isNewCustomerPageDispalay());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
