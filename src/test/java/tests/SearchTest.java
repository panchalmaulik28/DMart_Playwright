package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import constant.AppConstant;
import pages.DashboardPage;
import pages.HeaderPage;
import pages.SearchResultsPage;

public class SearchTest extends BaseTest {

	DashboardPage dashboardPage;
	HeaderPage headerPage;
	SearchResultsPage searchResultsPage;

	String productName = "Dry Fruits";
	String noProduct = "XYZ1";

	@BeforeClass
	public void object() {
		dashboardPage = new DashboardPage(page);
		headerPage = new HeaderPage(page);
		searchResultsPage = new SearchResultsPage(page);
		whereShouldWeDeliver();
	}

	@Test(priority = 0)
	public void shouldDisplay_NoSearchResultsFound() {
		headerPage.gloableSearch(noProduct);
		String value = dashboardPage.getNoProductFound();
		assertEquals(value, AppConstant.NO_RECORD_FOUND_TXT);
	}

	@Test(priority = 1)
	public void validateThatSearchResultWithKeyword() {
		headerPage.gloableSearch(productName);
		String value = dashboardPage.searchResult();
		assertEquals(value, productName);
	}

	String[] brandName = { "DMart Premia", "Nutraj", "Shamim" };
	String filterBy = "Brand";

	@Test(priority = 2)
	public void validateProductAndSearchResultCount() {
		headerPage.gloableSearch(productName);
		searchResultsPage.applyFilter("Brand", brandName);
		assertEquals(dashboardPage.searchCount(), dashboardPage.searchProductCount());
	}

	@Test(priority = 3)
	public void validateBadgeCountAfterAppliedFilter() {
		headerPage.gloableSearch(productName);
		searchResultsPage.applyFilter(filterBy, brandName);
		int countTemp = searchResultsPage.getFilterBadgeCount(filterBy);
		assertEquals(countTemp, brandName.length);
	}
}