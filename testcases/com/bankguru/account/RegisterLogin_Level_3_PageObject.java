package com.bankguru.account;

import org.testng.annotations.Test;

import PageObjects.HomePageObject;
import PageObjects.LoginPageObject;
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

public class RegisterLogin_Level_3_PageObject extends AbstractTest {
	private WebDriver driver;
	private String email, userID, password, loginUrl;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;
	
	@Parameters("browser")
	@BeforeClass 
	public void beforeClass(String browserName)  {
		// Multi browser
		driver = openMultiBrowser(browserName);
		
		System.out.println("Driver:" +driver.toString());
		email = "seleniumonline" + randomNumber() + "@gmail.com";
		
		loginPage= new LoginPageObject(driver);
		
	}
	

	@Test
	public void TC_01_RegisterToSystem() throws Exception {
		
		loginUrl = loginPage.getLoginPageUrl();
		loginPage.clickToHereLink();
		
		//click here link => vao trang register
		registerPage = new RegisterPageObject(driver);
		
		registerPage.intputToEmailIDTextbox(email);
		registerPage.clickToSubmitButton();
		
		userID= registerPage.getUserIDText();
		password= registerPage.getPasswordText();
		
	
	}

	@Test
	public void TC_02_LoginWithAboveInformation() throws Exception {
		registerPage.openLoginPage(loginUrl);
		//open Login Url > vao Login Page lại
		loginPage= new LoginPageObject(driver);
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		
		//click to Login .trang Hompahe
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isHomePageDisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}

}
