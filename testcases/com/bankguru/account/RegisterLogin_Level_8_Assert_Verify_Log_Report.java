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

import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

public class RegisterLogin_Level_8_Assert_Verify_Log_Report extends AbstractTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private HomePageObject homePage;
	private NewCustomerPageObject newCustomerPage;
	
	
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
	public void TC_03_Assert_Verify_Log_Report() {
		
		System.out.println("TestCase_03-step_01: open new customer pgae");
		// số lượng page ít (vài chục)
		newCustomerPage = (NewCustomerPageObject) homePage.openDynamicPage(driver, "New Customer");	
		
		System.out.println("ATestCase_03-step_02: verify newcustomer page is displayed");
		verifyTrue(newCustomerPage.isNewCustomerPageDispalay());
		
		System.out.println("TestCase_03-step_03: verify customer form is undisplayed");
		verifyTrue(newCustomerPage.isAddCustomerFormUnDisplayed());
		
		System.out.println("TestCase_03-step_03: verify home page url is undisplayed");
		verifyTrue(newCustomerPage.isHomePageUnDispalay());
	
	
	
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	
}
