package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;

import utilities.BrowserManager;

public class HeaderPage extends BrowserManager {

	private Locator dashbordPin = page.locator("//span[@class='header_title__h8YkR']");
	private Locator dashbordCity = page.locator("//div[@class='header_cityCntr__zUvCm']");
	private Locator searchBoxTxt = page.locator("//input[@id='scrInput']");
	private Locator searchBtn = page.locator("//div[contains(@class,'search_src')]/div/button");
	private Locator searchSuggestionBox = page.locator("//div[contains(@class,'header_header-container')]/div[2]/div/div[2]");
	private Locator emptySearchBox = page.locator("//div[contains(@class,'search_emptyResultCntr')]");
	private Locator loader = page.locator("(//div[contains(@class,'loader_loader')])[1]");
	private Locator loaderAddToCart = page.locator("//div[contains(@class,'cart-action_action-loader')]");
	private Locator allCategories = page.locator("//span[contains(@class,'categories-header')]");
	private Locator allCategoriesLinks = page.locator("//ul[contains(@class,'MuiList-root')]/li/a/div");
	private Locator categoriesHeaderListLinks = page.locator("//div[contains(@class,'categories-header_listDynamicItemLink')]");
	
	public String getPinCode() {
		return dashbordPin.textContent().trim();
	}

	public String getCity() {
		return dashbordCity.textContent().trim();
	}

	public void gloableSearch(String value) {
		page.waitForLoadState(LoadState.LOAD);
		searchBoxTxt.fill(value);
		searchBtn.click();
		BasePage.waitForLoaderToDisappear();
	}

	public void allCategories(String value) {
		allCategories.click();
		for (int i = 0; i <= allCategoriesLinks.count(); i++) {
			if (allCategoriesLinks.nth(i).textContent().trim().equals(value.trim())) {
				allCategoriesLinks.nth(i).click();
				break;
			}
		}
	}
}