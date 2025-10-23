package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class HeaderPage {
	Page page;
	BasePage basePage;
	
	private Locator dashbordPin;
	private Locator dashbordCity;
	private Locator searchBoxTxt;
	private Locator searchBtn;
	private Locator searchSuggestionBox;
	private Locator emptySearchBox;
	private Locator loader;
	private Locator loaderAddToCart;
	private Locator allCategories;
	private Locator allCategoriesLinks;
	private Locator categoriesHeaderListLinks;
	
	public HeaderPage(Page page) {
		this.page = page;
		initLocator();
		basePage = new BasePage(page);
	}

	private void initLocator() {
		dashbordPin = page.locator("//span[@class='header_title__h8YkR']");
		dashbordCity = page.locator("//div[@class='header_cityCntr__zUvCm']");
		searchBoxTxt = page.locator("//input[@id='scrInput']");
		searchBtn = page.locator("//div[contains(@class,'search_src')]/div/button");
		searchSuggestionBox = page.locator("//div[contains(@class,'header_header-container')]/div[2]/div/div[2]");
		emptySearchBox = page.locator("//div[contains(@class,'search_emptyResultCntr')]");
		loader = page.locator("(//div[contains(@class,'loader_loader')])[1]");
		loaderAddToCart = page.locator("//div[contains(@class,'cart-action_action-loader')]");
		allCategories = page.locator("//span[contains(@class,'categories-header')]");
		allCategoriesLinks = page.locator("//ul[contains(@class,'MuiList-root')]/li/a/div");
		categoriesHeaderListLinks = page.locator("//div[contains(@class,'categories-header_listDynamicItemLink')]");
	}
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
		basePage.waitForLoaderToDisappear();
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