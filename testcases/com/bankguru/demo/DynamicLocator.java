package com.bankguru.demo;

public class DynamicLocator {
	public static void main(String[] args) {
		String NEW_CUSTOMER_LINK="//a[text()='New Customer']";
		String FUNDTRANSFER_LINK="//a[text()='Fund Transfer']";
		String DEPOSITE_LINK="//a[text()='Deposit']";
		String NEW_ACCOUNT_LINK="//a[text()='New Account']";
		String HOMEPAGE_LINK="//a[text()='Manager']";
		String DYNAMIC_LINK1= "//a[text()='%s']";
		String DYNAMIC_LINK2= "//a[text()='%s']//a[text()='%s']";
		String DYNAMIC_LINK3= "//a[text()='%s']//a[text()='%s']//a[text()='%s']";
		String DYNAMIC_TABLE="//td[@data-key='females' and text()='%s']/following-sibling::td[@data-key='country' and text()='%s']/following-sibling::td[@data-key='males' and text()='%s']/preceding-sibling::td[@class='qgrd-actions']/button[@class='qgrd-edit-row-btn']";
		String AFRICA="//td[@data-key='females' and text()='12253515']/following-sibling::td[@data-key='country' and text()='AFRICA']/following-sibling::td[@data-key='males' and text()='12599691']/preceding-sibling::td[@class='qgrd-actions']/button[@class='qgrd-edit-row-btn']\r\n" + 
				"//td[@data-key='females' and text()='384187']/following-sibling::td[@data-key='country' and text()='Afghanistan']/following-sibling::td[@data-key='males' and text()='407124']/preceding-sibling::td[@class='qgrd-actions']/button[@class='qgrd-edit-row-btn']";
		
		clickToElemet(HOMEPAGE_LINK);
		clickToElemet(NEW_ACCOUNT_LINK);
		clickToElemet(DEPOSITE_LINK);
		clickToElemet(FUNDTRANSFER_LINK);
		clickToElemet(NEW_CUSTOMER_LINK);
		
		
		clickToElement(DYNAMIC_LINK1, "New Customer");
		clickToElement(DYNAMIC_LINK1, "Fund Transfer");
		clickToElement(DYNAMIC_LINK1, "Deposit");
		clickToElement(DYNAMIC_LINK1, "New Account");
		clickToElement(DYNAMIC_LINK1, "Manager");
		
		clickToElement(DYNAMIC_LINK2, "Female", "Afghanistan");
		
		clickToElement(DYNAMIC_LINK3, "Male", "Manila","123456");
		clickToElement(DYNAMIC_TABLE, "12253515", "AFRICA","407124");
		
	}

	public static void clickToElemet(String pageName) {
		//waitToElement
		//clickToElement
		System.out.println(pageName);
	}
	
	public static void clickToElement(String pageName, String dynamicValue) {
		//waitToElement
		//clickToElement
		System.out.println(String.format(pageName, dynamicValue));
	}
	
	public static void clickToElement(String pageName, String dynamicValue_01,String dynamicValue_02) {
		//waitToElement
		//clickToElement
		System.out.println(String.format(pageName, dynamicValue_01,dynamicValue_02));
	}
	
	public static void clickToElement(String pageName, String dynamicValue_01,String dynamicValue_02,String dynamicValue_03) {
		//waitToElement
		//clickToElement
	System.out.println(String.format(pageName, dynamicValue_01,dynamicValue_02,dynamicValue_03));
	}
	
	//Rest Parameter: hàm này sẽ bao gồm hết 3 hàm clickToElement phía trên
	public static void clickToElement(String locator, String... dynamicValue) {
		System.out.println("click to element"+ String.format(locator, (Object[]) dynamicValue));
		
	}
	
	
}
