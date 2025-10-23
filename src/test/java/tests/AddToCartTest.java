package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import constant.AppConstant;
import pages.HeaderPage;
import pages.MyCartPage;
import pages.ProductListPage;

public class AddToCartTest extends BaseTest {

	MyCartPage myCartPage;
	ProductListPage productListPage;
	HeaderPage headerPage;

	String brandName = "Amul";
	String productName = "Amul Pure Ghee Pouch (1 L) : 905 g";
	// String[] productNames = { "Amul" };

	@BeforeClass
	public void location() {
		myCartPage = new MyCartPage(page);
		productListPage = new ProductListPage(page);
		headerPage = new HeaderPage(page);
		whereShouldWeDeliver();
	}

	@Test(priority = 0)
	public void noRecordFound() {
		myCartPage.openMyCart();
		assertEquals(myCartPage.noRecordFound(), AppConstant.NO_RECORD_FOUND_MYCART);
		myCartPage.closeMyCart();
	}

	@Test(priority = 1)
	public void addToCart() {
		headerPage.gloableSearch(brandName);
		productListPage.clickOnAddToCartButton(productName);
		myCartPage.openMyCart();
		assertEquals(myCartPage.getProductTitle(productName), productName);
	}

	@Test(priority = 2)
	public void calculateItemCountOnMyCartButton() {
		headerPage.gloableSearch(productName);
		productListPage.clickOnAddToCartButton(productName);
		assertEquals(myCartPage.myCartItemCount(), "1");
	}

	@Test(priority = 3)
	public void calculateItemsCountOnMyCartButton() {
		myCartPage.clearCartAfterTest();
		headerPage.gloableSearch(productName);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		productListPage.clickOnAddToCartButton(productName);
//		assertEquals(myCartPage.myCartItemCount(), "1");
	}

}
