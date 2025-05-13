package base;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterSuite; 
import org.testng.annotations.BeforeSuite;

import pages.BasePage;
import pages.HeaderPage;
import utilities.BrowserManager;

public class BaseTest extends BrowserManager {

	@BeforeSuite
	public void init() {
		BrowserManager.browserInvoke();
		whereShouldWeDeliver();
	}

	@AfterSuite
	public void tearDown() {
		BrowserManager.closeBrowser();
	}
	
	public static void whereShouldWeDeliver() {
		HeaderPage headerPage = new HeaderPage();

		BasePage.selectArea("380013", "Ahmedabad, Gujarat, India");
		assertEquals(headerPage.getPinCode(), "380013");
		assertEquals(headerPage.getCity(), "Ahmedabad");
	}
}
