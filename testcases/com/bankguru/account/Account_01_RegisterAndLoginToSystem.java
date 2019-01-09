package com.bankguru.account;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Account_01_RegisterAndLoginToSystem {
	WebDriver driver;
	private String email, userID, password, loginUrl;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		email= "seleniumonline"+randomNumber()+"@gmail.com";
	  }
	
  @Test
  public void TC_01_RegisterToSystem() {
	  driver.get("http://demo.guru99.com/v4/index.php");
	  
	  loginUrl = driver.getCurrentUrl();
	  
	  driver.findElement(By.xpath("//a[text()='here']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//input[@name='emailid']")).isDisplayed());
	  driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  userID= driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
	  password= driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
  
  }
  
  @Test
  public void TC_02_LoginWithAboveInformation() {
	  driver.get(loginUrl);
	  
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : "+ userID+"']")).isDisplayed());
	  
	  
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
