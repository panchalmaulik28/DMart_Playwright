package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import utilities.BrowserManager;

public class BasePage extends BrowserManager {

	private static Locator whereShouldWeDeliverPopup = page.locator("//div[@role='dialog']");
	private static Locator searchForPincodeAreaTxt = page.locator("//input[@id='pincodeInput']");
	private static String searchResultLbl = "//div[contains(@class,'pincode-widget_listdiv')]/p";
	private static Locator countlistDD = page.locator("//ul[contains(@class,'pincode-widget_pincode-list')]/li/button/div");
	private static Locator numberlistDD = page.locator("//ul[contains(@class,'pincode-widget_pincode-list')]/li/button/div/b");
	private static Locator arealistDD = page.locator("//ul[contains(@class,'pincode-widget_pincode-list')]/li/button/div/div");
	private static Locator searchResultList = page.locator("//li[contains(@class,'pincode-widget_pincode-item')]/button");
	private static Locator confirmLocationBtn = page.locator("//div[contains(@class,'pincode-widget_success-cntr-footer')]/div/div[2]/button");

	public static void selectArea(String pinCode, String area) {
		if (whereShouldWeDeliverPopup.isVisible()) {
			searchForPincodeAreaTxt.fill(pinCode.trim());
			page.waitForSelector(searchResultLbl);
			int count = countlistDD.count();
			if (count > 0) {
				for (int i = 0; i <= count - 1; i++) {
					if (numberlistDD.nth(i).textContent().trim().equalsIgnoreCase(pinCode)
							&& arealistDD.nth(i).textContent().trim().equalsIgnoreCase(area)) {
						searchResultList.nth(i).click();
						waitForLoaderToDisappear();
						break;
					}
				}
			}
		} else {
			selectArea(pinCode, area);
		}
		confirmLocationBtn.click();
		page.waitForLoadState(LoadState.LOAD);
		page.waitForTimeout(6000);
	}

	public static void waitForLoaderToDisappear() {
		try {
			page.waitForSelector(".loader",
					new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(10000));
		} catch (PlaywrightException e) {
			System.out.println("⚠️ Loader did not disappear within timeout.");
		}
	}

}
