package base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import factory.BrowserFactory;

public class Base {

	@BeforeTest
	public void init() {
		BrowserFactory.browserInvoke();
	}

	@AfterTest
	public void tearDown() {
		BrowserFactory.closeBrowser();
	}
}
