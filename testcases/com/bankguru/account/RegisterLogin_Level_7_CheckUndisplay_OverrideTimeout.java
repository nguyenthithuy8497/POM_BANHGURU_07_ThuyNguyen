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

public class RegisterLogin_Level_7_CheckUndisplay_OverrideTimeout extends AbstractTest {
	private WebDriver driver;
	private String email, userID, password, loginUrl;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
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
	public void TC_03_CheckUndisplayed_OverrideTimeout() {
		
		// số lượng page ít (vài chục)
		newCustomerPage = (NewCustomerPageObject) homePage.openDynamicPage(driver, "New Customer");	
		//verify Newcustomer page display
		Assert.assertTrue(newCustomerPage.isNewCustomerPageDispalay());
		//verify Newcustomer Form Undisplay(có trong DOM -> không visible)
		Assert.assertTrue(newCustomerPage.isAddCustomerFormUnDisplayed());
		//verify Home Page Undisplay(Không có trong Dom)
		Assert.assertTrue(newCustomerPage.isHomePageUnDispalay());
	
	
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
