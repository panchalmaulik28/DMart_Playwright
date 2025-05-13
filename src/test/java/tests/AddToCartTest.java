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

	String productName = "Amul Taaza Toned Milk";
	
	MyCartPage myCartPage;
	ProductListPage productListPage;
	HeaderPage headerPage ;
	
	@BeforeClass
	public void location() {
		myCartPage = new MyCartPage();
		productListPage = new ProductListPage();
		headerPage = new HeaderPage();
	}

	@Test(priority = 1)
	public void noRecordFound() {
		myCartPage.openMyCart();
		assertEquals(myCartPage.noRecordFound(), AppConstant.NO_RECORD_FOUND_MYCART);
	}

	@Test(priority = 2)
	public void calculateItemCountOnMyCartButton() {
		headerPage.gloableSearch(productName);	
		productListPage.clickOnAddToCartButton(productName);
		assertEquals(myCartPage.myCartItemCount(), "1");
	}
	
	
//	public void caalculateItemCountOnMyCartButton() {
//		headerPage.gloableSearch(productName);	
//		productListPage.clickOnAddToCartButton(productName);
//		assertEquals(myCartPage.myCartItemCount(), "1");
//	}

}
