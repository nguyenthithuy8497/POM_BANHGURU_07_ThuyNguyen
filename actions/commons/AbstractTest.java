package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.apache.log4j.AppenderSkeleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;


public class AbstractTest {
	private WebDriver driver;

	protected WebDriver openMultiBrowser(String browserName ) {
		
		if(browserName.equals("firefox")) {
			//WebDriverManager.firefoxdriver().version("47.0.2").setup();
			//WebDriverManager.firefoxdriver().setup();
			//WebDriverManager.firefoxdriver().arch32().setup();
			System.setProperty("webdriver.gecko.driver", ".\\resources\\geckodriver.exe");
			driver= new FirefoxDriver();
		} else if(browserName.equals("chrome")) {
			//System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
			WebDriverManager.chromedriver().version("2.41").setup();
			driver= new ChromeDriver();
		} else if (browserName.equals("chromeheadless")){
			//System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("window-size= 1366x768");
			driver= new ChromeDriver(chromeOptions);
		} else if(browserName.equals("internetexplore")) {
			WebDriverManager.iedriver().arch64().setup();
		}
		driver.get(Constants.DEV_URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("driver: "+driver.toString());

		return driver;
	}
	protected int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}
	private boolean checkPassed(boolean condition) {
    	boolean pass = true;
		try {
			if (condition == true)
				System.out.println("===PASSED===");
			else
				System.out.println("===FAILED===");
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkPassed(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true)
				System.out.println("===PASSED===");
			else
				System.out.println("===FAILED===");
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
    	boolean pass = true;
		boolean status;
		try {
			if (actual instanceof String && expected instanceof String) {
				actual = actual.toString().trim();
				System.out.println("Actual = " + actual);
				expected = expected.toString().trim();
				System.out.println("Expected = " + expected);
				status = (actual.equals(expected));
			} else {
				// Array A [5] = |5 |1 |7 |8 |10 |
				// Array B [5] = |5 |1 |7 |8 |10 |
				status = (actual == expected);
			}

			System.out.println("Compare value = " + status);
			if (status) {
				System.out.println("===PASSED===");
			} else {
				System.out.println("===FAILED===");
			}
			Assert.assertEquals(actual, expected, "Value is not matching!");
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
	protected void closeBrowserAndDriver(WebDriver driver) {
    	try {
			String osName = System.getProperty("os.name").toLowerCase();
			System.out.println("OS name = " + osName);

			String cmd = "";
			if (driver != null) {
				driver.quit();
			}

			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
					Process process = Runtime.getRuntime().exec(cmd);
					process.waitFor();
				}
			}
			System.out.println("---------- QUIT BROWSER SUCCESS ----------");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
	
