package base;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import pages.BasePage;
import pages.HeaderPage;
import utilities.ConfigReader;

public class BaseTest {

	static Playwright playwright;
	static Browser browser;
	static BrowserContext bc;
	public static Page page;

	@BeforeTest
	public static Page browserInvoke() {

		String browserName = ConfigReader.properties("browserName");
		String url = ConfigReader.properties("url");

		playwright = Playwright.create();

		switch (browserName) {
		case "chrome":
			browser = playwright.chromium().launch(new LaunchOptions().setChannel(browserName).setHeadless(false));
			bc = browser.newContext();
			break;
		case "firefox":
			browser = playwright.firefox().launch(new LaunchOptions().setHeadless(false));
			bc = browser.newContext();
			break;
		case "safari":
			browser = playwright.webkit().launch(new LaunchOptions().setHeadless(false));
			bc = browser.newContext();
			break;
		default:
			System.out.println("Wrong Browser Name..!");
			break;
		}
		page = bc.newPage();
		page.navigate(url);
		return page;
	}

	@AfterTest
	public static void closeBrowser() {
		if (bc != null)
			bc.close();
		if (browser != null)
			browser.close();
		if (playwright != null)
			playwright.close();
	}

	public static void whereShouldWeDeliver() {
		HeaderPage headerPage = new HeaderPage(page);
		BasePage basePage = new BasePage(page);

		basePage.selectArea("380013", "Ahmedabad, Gujarat, India");
		assertEquals(headerPage.getPinCode(), "380013");
		assertEquals(headerPage.getCity(), "Ahmedabad");
		try {
			Thread.sleep(Duration.ofSeconds(3));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
