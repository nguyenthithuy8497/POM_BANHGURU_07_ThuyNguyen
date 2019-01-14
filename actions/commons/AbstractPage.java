package commons;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
	// -----------------------WEB BROWSER-----------------------
	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancleAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void sendkeyTextAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);

	}
	public void waitForAlertPresence(WebDriver driver) {
			WebDriverWait waitEplixit = new WebDriverWait(driver, 30);
			waitEplixit.until(ExpectedConditions.alertIsPresent());
			
		}
	
	//------------------------WEB ELEMENT----------------------
	//1
	public void clickToElement(WebDriver driver, String locator) {
		WebElement element =driver.findElement(By.xpath(locator));
		element.click();
	}
	//2
	public void senkeyToElement(WebDriver driver, String locator,String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(value);
	}
	//3
	public void selectItemInHtmlDropdown(WebDriver driver, String locator, String valueDropdown) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		select.selectByVisibleText(valueDropdown);
		
	}
	//4
	public String getItemInHtmlDropdown(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}
	//5
	public void selectItemCustomDropdown(WebDriver driver, String scrollToXpath, String parentList,String childXpath, String expectItem ) throws Exception {
		WebDriverWait waitExplicit = new WebDriverWait(driver, 30);
		JavascriptExecutor javaExecutor = (JavascriptExecutor) driver;
		
		//Scroll tới element cha
		javaExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(scrollToXpath)));
		Thread.sleep(1000);

		//Click vào dropdown
		WebElement element = driver.findElement(By.xpath(parentList));
		element.click();
		Thread.sleep(1000);
		//Get tất cả item trong dropdown vào 1 list element (List <WebElement>)
		List<WebElement> childList = driver.findElements(By.xpath(childXpath));
		
		//Wait để tất cả phần tử trong dropdown được hiển thị
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(childXpath))));
		
		//Dùng vòng lặp for duyệt qua từng phần tử sau đó getText
		for(WebElement child:childList) {
			String textItem = child.getText().trim(); //trim(): Xóa các ký tự trắng, tab,.. ở đầu và ở cuối
			System.out.println("Text in dropdown= " +textItem);
			
		//	Nếu actual text = expected text thì click vào phần tử đó và break khỏi vòng lặp

			if(textItem.equals(expectItem)) {
				//scroll tới expecteed item để click 
				javaExecutor.executeScript("arguments[0].scrollIntoView(true);", child);
				child.click();
				Thread.sleep(1000);
				break;
			}
		}
	}
	//6
	public String getAttributeValueInElement(WebDriver driver, String locator, String attributeName) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}
	//7
	public String getTextElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}
	//8
	public int countElementNumber(WebDriver driver, String locator) {
		List<WebElement> elements= driver.findElements(By.xpath(locator));
		return elements.size();
	
	}
	//9
	public void checkTheCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(!element.isSelected()) {
			element.click();
		}		
	}
	//10
	public void uncheckTheCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isSelected()) {
			element.click();
		}		
	}
	//11	
	public boolean isControlDisplayed(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}
	//12
	public boolean isControlSelected(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}
	//13
	public boolean isControlEnabled(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}
	//14
	public void switchToWindowByID(WebDriver driver, String parent) {
		//get ra tat ca cac windown dang co
        Set<String> allWindows = driver.getWindowHandles();
        //dung vong lap de kiem tra
        for (String runWindow : allWindows) {
        	System.out.println(runWindow);
        	//moi lan duyet kiem tra dieu kien
                    if (!runWindow.equals(parent)) {
                                driver.switchTo().window(runWindow);
                                break;
                    }
        }
}
	 public void switchToWindowByTitle(WebDriver driver, String title) {
         Set<String> allWindows = driver.getWindowHandles();
         //Dùng vòng lặp kiểm tra tất cả cửa sổ đang có
         for (String runWindows : allWindows) {
         	//switch qua từng cửa sổ trc sau đó ktra
                     driver.switchTo().window(runWindows);
                     //get ra title của page ms switch
                     String currentWin = driver.getTitle();
                     //Kiểm tra neesu title of page = title truyền vào
                     if (currentWin.equals(title)) {
                                 break;
                     }
         }
}

	 public void closeAllWithoutParentWindows(WebDriver driver, String parentWindow) {
	    	//get ra ID cua tat ca cac cua so
	                Set<String> allWindows = driver.getWindowHandles();
	                //dung vong lap duyet qua tat ca ID
	                for (String runWindows : allWindows) {
	                            if (!runWindows.equals(parentWindow)) {
	                                        driver.switchTo().window(runWindows);
	                                        driver.close();
	                            }
	                }
	                driver.switchTo().window(parentWindow);
	               
	    }
	 public void clickElementByJavascript(WebDriver driver, String locator) {
		 WebElement element = driver.findElement(By.xpath(locator));
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].click();", element);
		}
	 
	 public void switchToIframe(WebDriver driver, String locator) {
		 List<WebElement> elements = driver.findElements(By.xpath(locator));
		 if(elements.size()>0) {
			 driver.switchTo().frame(elements.get(0));
			 driver.switchTo().defaultContent();
		 }
		}
	 
	
	//15
	public void doubleClickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions actions = new Actions(driver);
		actions.doubleClick(element);
	}
	public void hoverMouseToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
	}
	public void rightClickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions actions = new Actions(driver);
		actions.contextClick(element);
	}
	public void dragAndDropElement(WebDriver driver, String locatorResoure, String locatorTarget) {
		WebElement resource = driver.findElement(By.xpath(locatorResoure));
		WebElement target = driver.findElement(By.xpath(locatorTarget));
		Actions actions = new Actions(driver);
		actions.dragAndDrop(resource, target).perform();
	}
	public void keyPressElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).build().perform();
	}

	//16
	public void sendkeySingleFile(WebDriver driver, String locator, String filePath) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(filePath);
	}
	public void sendkeyMultiFile(WebDriver driver, String locator, String filePath1, String filePath2) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(filePath1+ "\n" +filePath2);
		List<WebElement> startButton = driver.findElements(By.xpath(locator));
		for(WebElement startBtn: startButton) {
			startBtn.click();
		}
	}
	public void autoIT(WebDriver driver, String locator, String addressFile, String AddressBrowser) throws IOException {
		driver.findElement(By.xpath(locator)).click();
		String filePath = addressFile;
		Runtime.getRuntime().exec(new String[] { AddressBrowser, filePath });

		if (driver.toString().toLowerCase().contains("chrome")) {
		    driver.findElement(By.cssSelector(".fileinput-button")).click();
		    } else if (driver.toString().toLowerCase().contains("firefox")) {
		//    clickToElementByJS("//input[@type='file']");
		  }
		
	}
	public void robot(WebDriver driver) throws Exception{
		//Specify the file location with extension
		StringSelection select = new StringSelection("E:\\PROJECT TRAINING\\Selenium_Training\\upload\\image.png");

		//Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		//Click
		driver.findElement(By.className("fileinput-button")).click();

		Robot robot = new Robot();
		

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	//17
	public Object executeForBrowser(WebDriver driver,String javaSript) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript(javaSript);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
	public Object executeForElement(WebDriver driver,String javaSript, String locator) {
		WebElement element = driver.findElement(By.id(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		return null;
	}
	
	
	 public Object scrollToBottomPage(WebDriver driver) {
	        try {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	        } catch (Exception e) {
	            e.getMessage();
	            return null;
	        }
	    }
	 public Object ScrollToElement(WebDriver driver,String locator) {
			WebElement element = driver.findElement(By.id(locator));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			return null;
	    }
	 
	 public void highlightElement(WebDriver driver, String locator) {
		 WebElement element = driver.findElement(By.xpath(locator));
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].style.border='3px groove red'", element);
	    }
	 public Object clickToElementByJS(WebDriver driver, String locator) {
	        try {
	        	WebElement element = driver.findElement(By.xpath(locator));
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return js.executeScript("arguments[0].click();", element);
	        } catch (Exception e) {
	            e.getMessage();
	            return null;
	        }
	    }
	 public Object sendkeyToElementByJS(WebDriver driver,String locator, String value) {
	        try {
	        	WebElement element = driver.findElement(By.xpath(locator));
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	        } catch (Exception e) {
	            e.getMessage();
	            return null;
	        }
	    }

	    public Object removeAttributeInDOM(WebDriver driver,String locator, String attribute) {
	        try {
	        	WebElement element = driver.findElement(By.xpath(locator));
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	        } catch (Exception e) {
	            e.getMessage();
	            return null;
	        }
	    }
	    public Object navigateToUrlByJS(WebDriver driver, String url) {
	        try {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return js.executeScript("window.location = '" + url + "'");
	        } catch (Exception e) {
	            e.getMessage();
	            return null;
	        }
	    }
	//18
	public void waitToElementVisible(WebDriver driver, String locator) {
		By byLocator = By.xpath(locator);
		WebDriverWait waitEplixit = new WebDriverWait(driver, 30);
		waitEplixit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		
	}
	public void waitToElementPresence(WebDriver driver, String locator) {
		By byLocator = By.xpath(locator);
		WebDriverWait waitEplixit = new WebDriverWait(driver, 30);
		waitEplixit.until(ExpectedConditions.presenceOfElementLocated(byLocator));
	}
	public void waitToElementInvisible(WebDriver driver, String locator) {
		By byLocator = By.xpath(locator);
		WebDriverWait waitEplixit = new WebDriverWait(driver, 30);
		waitEplixit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));	
	}
	
	
	
}
