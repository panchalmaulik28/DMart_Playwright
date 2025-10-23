package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BasePage {

	Page page;
	
	private Locator whereShouldWeDeliverPopup;
	private Locator searchForPincodeAreaTxt;
	private String searchResultLbl;
	private Locator countlistDD;
	private Locator numberlistDD;
	private Locator arealistDD;
	private Locator searchResultList;
	private Locator confirmLocationBtn;
	
	public BasePage(Page page) {
		this.page = page;
		initLocator();
	}

	private void initLocator() {
		whereShouldWeDeliverPopup = page.locator("//div[@role='dialog']");
		searchForPincodeAreaTxt = page.locator("//input[@id='pincodeInput']");
		searchResultLbl = "//div[contains(@class,'pincode-widget_listdiv')]/p";
		countlistDD = page.locator("//ul[contains(@class,'pincode-widget_pincode-list')]/li/button/div");
		numberlistDD = page.locator("//ul[contains(@class,'pincode-widget_pincode-list')]/li/button/div/b");
		arealistDD = page.locator("//ul[contains(@class,'pincode-widget_pincode-list')]/li/button/div/div");
		searchResultList = page.locator("//li[contains(@class,'pincode-widget_pincode-item')]/button");
		confirmLocationBtn = page.locator("//div[contains(@class,'pincode-widget_success-cntr-footer')]/div/div[2]/button");
	}


	public void selectArea(String pinCode, String area) {
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
		
	}

	public  void waitForLoaderToDisappear() {
		try {
			page.waitForSelector(".loader", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(10000));
		} catch (PlaywrightException e) {
			System.out.println("⚠️ Loader did not disappear within timeout.");
		}
	}

}
