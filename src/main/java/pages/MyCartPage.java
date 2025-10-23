package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;

public class MyCartPage {
	Page page;

	private Locator cartBtn;
	private Locator closeMyCartBtn;
	private Locator myCartItemCount;
	private String cartLoader;
	private Locator myCartProductList;
	private Locator productTitleInCart;
	private Locator myCartProductRemove;
	private Locator myCartFirstProduct;

	/*---------String--------*/
	private Locator noRecordFound;

	public MyCartPage(Page page) {
		this.page = page;
		initLocator();
	}

	private void initLocator() {
		cartBtn = page.locator("//div[contains(@class,'header_cartCntr')]/button");
		closeMyCartBtn = page.locator("//div[contains(@class,'mini-cart_close_icon')]/button");
		myCartItemCount = page.locator("//span[contains(@class,'header_count')]");
		cartLoader = "//div[contains(@class,'mini-cart_action-loader')]";
		myCartProductList = page.locator("//div[contains(@class,'mini-cart_product')]");
		productTitleInCart = page.locator("//div[contains(@class,'mini-cart-card_content-container')]/div/a/div");
		myCartProductRemove = page.locator("//div[contains(@class,'mini-cart_container')]/div[2]/div/div/div/div[3]/div/div[3]/div/div/button");
		myCartFirstProduct = page.locator("//div[contains(@class,'mini-cart_product')][1]");

		/*---------String--------*/
		noRecordFound = page.locator("//p[contains(@class,'cart-empty_heading')]");
	}

	public String noRecordFound() {
		return noRecordFound.textContent().trim();
	}

	public void openMyCart() {
		cartBtn.click();
		waitForLoaderToDisappearInMyCart();
	}

	public void closeMyCart() {
		closeMyCartBtn.click();
	}

	public void clearCartAfterTest() {
		if (!myCartItemCount().equals("0")) {
			openMyCart();
			myCartFirstProduct.waitFor();
			int productListCount = myCartProductList.count();
			System.out.println("2. productListCount = " + productListCount);
			if (productListCount <= 1) {
				for (int i = 1; i <= productListCount; i++) {
					myCartProductRemove.nth(i).waitFor();
					myCartProductRemove.nth(i).click();
				}
				waitForLoaderToDisappearInMyCart();
				closeMyCart();
			} else {
				closeMyCart();
			}
		}
	}

	public String myCartItemCount() {
		return myCartItemCount.textContent().trim();
	}

	public void waitForLoaderToDisappearInMyCart() {
		try {
			page.waitForSelector(cartLoader, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(5000));
		} catch (PlaywrightException e) {
			System.out.println("Loader did not disappear within timeout.");
		}
	}

	public String getProductTitle(String productName) {
		int productItemCount = productTitleInCart.count();
		System.out.println(productItemCount);
		
		String productTitleInCartStr = null;
		for (int i = 0; i < productItemCount; i++) {
			System.out.println(productTitleInCart.nth(i).textContent().trim());
			if (productTitleInCart.nth(i).textContent().trim().contains(productName)) {
				productTitleInCartStr = productTitleInCart.nth(i).textContent().trim();
			}
		}
		return productTitleInCartStr;
	}
}
