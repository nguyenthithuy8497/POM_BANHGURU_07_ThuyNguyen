package PageUI;

public class AbstractPageUI {
//chua 13 xpath dai dien cho 13 url
	public static final String NEW_CUSTOMER_LINK="//a[text()='New Customer']";
	public static final String FUNDTRANSFER_LINK="//a[text()='Fund Transfer']";
	public static final String DEPOSITE_LINK="//a[text()='Deposit']";
	public static final String NEW_ACCOUNT_LINK="//a[text()='New Account']";
	public static final String HOMEPAGE_LINK="//a[text()='Manager']";
	//dynamic link
	public static final String DYNAMIC_LINK="//a[text()='%s']";
	
	public static final String DYNAMIC_TEXTBOX_BUTTON="//input[@name='%s']";
	public static final String DYNAMIC_RADIO_BUTTON="//input[@value='%s']";
	public static final String DYNAMIC_TEXT_AREA="//textarea[@name='%s']";
	
	public static final String DYNAMIC_PAGEHAEDING="//p[@class='heading3' and text()='%s']";
	
	public static final String DYNAMIC_TABLE_ROWNAME="//td[text()='%s']/following-sibling::td";
	
}
