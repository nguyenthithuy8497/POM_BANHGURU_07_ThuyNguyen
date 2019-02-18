package com.bankguru.account;

import org.testng.annotations.Test;

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

public class RegisterLogin_Level_2_AbstractPage_MultiBrowser extends AbstractTest {
	private WebDriver driver;
	private String email, userID, password, loginUrl;
	private AbstractPage abstractPage;
	@Parameters("browser")
	@BeforeClass 
	public void beforeClass(String browserName)  {
		// Multi browser
		driver = openMultiBrowser(browserName);
		System.out.println("Driver:" +driver);
		abstractPage = new AbstractPage();
		email = "seleniumonline" + randomNumber() + "@gmail.com";
	}

	@Test
	public void TC_01_RegisterToSystem() {
		abstractPage.openUrl(driver, "http://demo.guru99.com/v4/index.php");
		loginUrl = abstractPage.getCurrentUrl(driver);
		abstractPage.clickToElement(driver, "//a[text()='here']");
		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//input[@name='emailid']"));
		abstractPage.senkeyToElement(driver, "//input[@name='emailid']", email);
		abstractPage.clickToElement(driver, "//input[@name='btnLogin']");
		userID = abstractPage.getTextElement(driver, "//td[text()='User ID :']/following-sibling::td");
		password = abstractPage.getTextElement(driver, "//td[text()='Password :']/following-sibling::td");

	}

	@Test
	public void TC_02_LoginWithAboveInformation() {
		abstractPage.openUrl(driver, loginUrl);
		abstractPage.senkeyToElement(driver, "//input[@name='uid']", userID);
		abstractPage.senkeyToElement(driver, "//input[@name='password']", password);
		abstractPage.clickToElement(driver, "//input[@name='btnLogin']");
		Assert.assertTrue(abstractPage.isControlDisplayed(driver,
				"//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"));
		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//td[text()='Manger Id : " + userID + "']"));
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
