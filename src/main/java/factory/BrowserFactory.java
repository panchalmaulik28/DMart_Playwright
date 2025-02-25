package factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {

	static Playwright playwright;
	static Browser browser;
	static BrowserContext bc;
	static Properties prop;
	public static Page page;

	public static void browserInvoke() {
		prop = properties();
		String browserName = (String) prop.get("browserName");
		String url = (String) prop.get("url");
		
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new LaunchOptions().setChannel(browserName).setHeadless(false));
		bc = browser.newContext();
		page = bc.newPage();
		page.navigate(url);
	}

	public static void closeBrowser() {
		page.close();
	}

	public static Properties properties() {
		try {
			prop = new Properties();
			FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
