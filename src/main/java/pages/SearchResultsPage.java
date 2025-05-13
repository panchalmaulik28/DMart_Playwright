package pages;

import com.microsoft.playwright.Locator;

import utilities.BrowserManager;

public class SearchResultsPage extends BrowserManager{

	/*--------filter-------*/
	private Locator filter_badgeCountList = page.locator("//div[contains(@class,'count-badge')]");
	private Locator filter_textList = page.locator("//div[contains(@class,'search-sort-filters_filter-container')]/div/p");
	private Locator filter_DDTextList = page.locator("//div[contains(@class,'filter-sort-modal_filter-options-inner-container')]/label/span[2]/p");
	private Locator filter_applyBtn = page.locator("//button[contains(@class,'filters-actions-buttons_apply-button')]");
	private Locator filter_clearAllBtn = page.locator("//button[contains(@class,'filters-actions-buttons_clear-button')]");
	
	//Filter applied logic
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
					BasePage.waitForLoaderToDisappear();
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
