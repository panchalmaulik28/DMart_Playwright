package utilities;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserManager {

	static Playwright playwright;
	static Browser browser;
	static BrowserContext bc;
	public static Page page;

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

	public static void closeBrowser() {
		if (bc != null)
			bc.close();
		if (browser != null)
			browser.close();
		if (playwright != null)
			playwright.close();
	}
}