package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.apache.log4j.AppenderSkeleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;


public class AbstractTest {
	private WebDriver driver;
	//get project path
	private final String workingDir = System.getProperty("user.dir");

	protected WebDriver openMultiBrowser(String browserName ) {
		
		if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().arch32().setup();
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, workingDir + "\\FirefoxLog.txt");
			FirefoxProfile profile = new FirefoxProfile();
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			profile.setAcceptUntrustedCertificates(false);
			profile.setAssumeUntrustedCertificateIssuer(true);
			profile.setPreference("dom.webnotifications.enable", false);
			profile.setPreference("dom.dowload.folderList", 2);
			profile.setPreference("dom.helperApps.alwaysAsk.force", false);
			profile.setPreference("dom.dowload.manager.showWhenStarting", false);
			profile.setPreference("dom.dowload.dir", "C:\\Dowloads");
			profile.setPreference("dom.dowload.dowloadDir", "C:\\Dowloads");
			profile.setPreference("dom.dowload.defaultFolder", "C:\\Dowloads");
			profile.setPreference("dom.helperApps.neverAsk.saveToDisk", "text/anytext,text/plain, text/html,application/plain");
			
			FirefoxOptions options = new FirefoxOptions();
			driver = new FirefoxDriver(options);
		
		} 
		else if(browserName.equals("firefoxheadless")) {
			//c1
		//	FirefoxOptions options = new FirefoxOptions();
		//	options.setHeadless(true);
			
			//c2
			WebDriverManager.firefoxdriver().setup();
			FirefoxBinary firefoxBinary = new FirefoxBinary();
			firefoxBinary.addCommandLineOptions("--headless");
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, workingDir + "\\FirefoxHeadlessLog.txt");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setBinary(firefoxBinary);
			driver = new FirefoxDriver(firefoxOptions);
			
		}
		else if(browserName.equals("chrome")) {
			//System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--disable-extensions");
			options.addArguments("disable-infobars");
			options.addArguments("start-maximize");
			driver= new ChromeDriver(options);
		} else if (browserName.equals("chromeheadless")){
			//System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("window-size= 1366x768");
			driver= new ChromeDriver(chromeOptions);
		} else if(browserName.equals("ie")) {
			//System.setProperty("webdriver.ie.driver", ".\\resources\\IEDriverServer.exe");
			WebDriverManager.iedriver().arch32().setup();
			
			DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capability.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
			capability.setCapability(InternetExplorerDriver.NATIVE_EVENTS,false);
			capability.setCapability("ignoreProtectedModeSettings", true);
			capability.setCapability("ignoreZoomSetting",  true);
			capability.setCapability("requireWindowFocus", true);
			capability.setJavascriptEnabled(true);
			capability.setCapability("enableElementCacheCleanup", true);
			capability.setBrowserName("internet explorer");
			capability.setPlatform(org.openqa.selenium.Platform.ANY);
			
			driver = new InternetExplorerDriver(capability);
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
	
