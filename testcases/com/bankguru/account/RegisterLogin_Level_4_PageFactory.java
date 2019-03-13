package com.bankguru.account;

import org.testng.annotations.Test;

import PageObjects.HomePageObject;
import PageObjects.LoginPageObject;
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

public class RegisterLogin_Level_4_PageFactory extends AbstractTest {
	private WebDriver driver;
	private String email, userID, password, loginUrl;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;
	
	@Parameters("browser")
	@BeforeClass 
	public void beforeClass(String browserName)  {
		driver = openMultiBrowser(browserName);
		email = "seleniumonline" + randomNumber() + "@gmail.com";
		// Mo url > vao LoginPage
		loginPage= PageFactoryManager.getLoginPage(driver);
		
	}
	

	@Test
	public void TC_01_RegisterToSystem() throws Exception {
		
		loginUrl = loginPage.getLoginPageUrl();
		registerPage=loginPage.clickToHereLink();
		registerPage.intputToEmailIDTextbox(email);
		registerPage.clickToSubmitButton();
		
		userID= registerPage.getUserIDText();
		password= registerPage.getPasswordText();
		
	
	}

	@Test
	public void TC_02_LoginWithAboveInformation() throws Exception {
		loginPage = registerPage.openLoginPage(loginUrl);
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isHomePageDisplayed());
		
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
