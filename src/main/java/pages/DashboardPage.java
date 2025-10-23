package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DashboardPage {

	Page page;

	private Locator searchResultContent;
	private Locator searchCount;
	private Locator productCount;
	private Locator noProductFound;

	public DashboardPage(Page page) {
		this.page = page;
		initLocator();
	}

	private void initLocator() {
		searchResultContent = page.locator("//h1[contains(@class,'search-landing_searchHeaderDiv')]/span/b");
		searchCount = page.locator("//h1[contains(@class,'search-landing_searchHeaderDiv')]/span");
		productCount = page.locator("//div[contains(@class,'search-landing')]/div //div[contains(@class,'vertical-card_card')]");
		noProductFound = page.locator("(//div[contains(@class,'notFound-search_container')]/span)[2]");
	}

	public String searchResult() {
		String value = searchResultContent.textContent();
		String[] temp = value.split("\"");
		String[] temp1 = temp[1].split("\"");
		return temp1[0];
	}

	public int searchCount() {
		String value = searchCount.textContent();
		String[] temp = value.split(" ");
		int i = Integer.parseInt(temp[2]);
		return i;
	}

	public int searchProductCount() {
		int count = productCount.count();
		return count;
	}

	public String getNoProductFound() {
		return noProductFound.textContent().trim();
	}
}
