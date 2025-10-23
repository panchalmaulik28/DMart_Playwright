package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchResultsPage {
	
	Page page;
	BasePage basePage;
	
	/*--------filter-------*/
	private Locator filter_badgeCountList;
	private Locator filter_textList;
	private Locator filter_DDTextList;
	private Locator filter_applyBtn;
	private Locator filter_clearAllBtn;
	
	public SearchResultsPage(Page page) {
		this.page = page;
		basePage = new BasePage(page);
		initLocator();
	}

	private void initLocator() {
		filter_badgeCountList = page.locator("//div[contains(@class,'count-badge')]");
		filter_textList = page.locator("//div[contains(@class,'search-sort-filters_filter-container')]/div/p");
		filter_DDTextList = page.locator("//div[contains(@class,'filter-sort-modal_filter-options-inner-container')]/label/span[2]/p");
		filter_applyBtn = page.locator("//button[contains(@class,'filters-actions-buttons_apply-button')]");
		filter_clearAllBtn = page.locator("//button[contains(@class,'filters-actions-buttons_clear-button')]");
	}

	// Filter applied logic
	public void applyFilter(String searchValue, String brandName[]) {
		if (filter_textList.count() > 0) {
			for (int i = 0; i <= filter_textList.count() - 1; i++) {
				if (filter_textList.nth(i).textContent().trim().equals(searchValue.trim())) {
					filter_textList.nth(i).click();
					for (int j = 0; j <= filter_DDTextList.count() - 1; j++) {
						String temp = filter_DDTextList.nth(j).textContent().trim();
						filter_DDTextList.nth(j).scrollIntoViewIfNeeded();
						for (String expected : brandName) {
							if (temp.contains(expected)) {
								filter_DDTextList.nth(j).click();
							}
						}
					}
					filter_applyBtn.click();
					basePage.waitForLoaderToDisappear();
					break;
				}
			}
		} else {
			applyFilter(searchValue, brandName);
		}
	}

	public Integer getFilterBadgeCount(String filterBy) {
		int totalFilter = filter_textList.count();
		String badgeCountTemp = null;

		for (int i = 0; i <= totalFilter; i++) {
			if (filter_textList.nth(i).textContent().trim().equalsIgnoreCase(filterBy.trim())) {
				badgeCountTemp = filter_badgeCountList.nth(i).textContent().trim();
				break;
			}
		}
		return Integer.parseInt(badgeCountTemp);
	}
}
