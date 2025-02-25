package pages;

import com.microsoft.playwright.Locator;

import factory.BrowserFactory;

public class WhereShouldWeDeliver extends BrowserFactory {

	Locator whereShouldWeDeliverPopup = page.locator("//div[@role='dialog']");
	Locator searchForPincodeAreaTxt = page.locator("//input[@id='pincodeInput']");
	Locator listCountDD = page.locator("//ul[contains(@class,'pincode-widget_pincode-list')]/li/button/div");
	Locator listNumberDD = page.locator("//ul[contains(@class,'pincode-widget_pincode-list')]/li/button/div/b");
	Locator listAreaDD = page.locator("//ul[contains(@class,'pincode-widget_pincode-list')]/li/button/div/div");
	Locator confirmLocationBtn = page.locator("//div[contains(@class,'pincode-widget_success-cntr-footer')]/div/div[2]/button");
	String searchResultLbl = "//div[contains(@class,'pincode-widget_listdiv')]/p";

	public void selectArea(String pinCode, String area) {
		if (whereShouldWeDeliverPopup.isVisible()) {
			searchForPincodeAreaTxt.fill(pinCode.trim());
			page.waitForSelector(searchResultLbl);
			for (int i = 0; i <= listCountDD.count() - 1; i++) {
				if (listNumberDD.nth(i).textContent().trim().equalsIgnoreCase(pinCode)
						&& listAreaDD.nth(i).textContent().trim().equalsIgnoreCase(area)) {
					listCountDD.nth(i).click();
					break;
				}
			}
		} else {
			selectArea(pinCode, area);
		}
		confirmLocationBtn.click();
	}
}
