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

public class RegisterLogin_Global_Register extends AbstractTest {
	
	
	@Parameters("browser")
	@BeforeClass 
	public void beforeClass(String browserName)  {
		driver = openMultiBrowser(browserName);
		email = "seleniumonline" + randomNumber() + "@gmail.com";
		// Mo url > vao LoginPage
		loginPage= PageFactoryManager.getLoginPage(driver);
		
	}
	

	@Test
	public void TC_01_RegisterToSystem(Method testMethod) throws Exception {
		System.out.println("=========Start: " +testMethod.getName()+"======");

		System.out.println("TestCase_01-Step_01: click to here link");
		registerPage=loginPage.clickToHereLink();
		
		System.out.println("TestCase_01-Step_02:input Data to email textbox");
		registerPage.intputToEmailIDTextbox(email);
		
		System.out.println("TestCase_01-Step_03:click to Submit button");
		registerPage.clickToSubmitButton();
		
		System.out.println("TestCase_01-Step_04:get user and password information");
		USER_ID= registerPage.getUserIDText();
		PASSWORD= registerPage.getPasswordText();
		System.out.println("=========Finish: " +testMethod.getName()+"======");

	
	}
	
	
	
	
	

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	private WebDriver driver;
	private String email;
	public static String USER_ID, PASSWORD;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	
	
}
