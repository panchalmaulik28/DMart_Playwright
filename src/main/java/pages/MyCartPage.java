package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;

import utilities.BrowserManager;

public class MyCartPage extends BrowserManager{
	
	private Locator cartBtn = page.locator("//div[contains(@class,'header_cartCntr')]/button");
	private Locator myCartItemCount = page.locator("//span[contains(@class,'header_count')]");
	private Locator noRecordFound = page.locator("//p[contains(@class,'cart-empty_heading')]");
	private String cartLoader = "//div[contains(@class,'mini-cart_action-loader')]";
	
	public String noRecordFound() {
		return noRecordFound.textContent().trim();
	}

	public void openMyCart() {
		cartBtn.click();
		waitForLoaderToDisappearInMyCart();
	}

	public String myCartItemCount() {
		return myCartItemCount.textContent().trim();
	}

	public void waitForLoaderToDisappearInMyCart() {
		try {
			page.waitForSelector(cartLoader,
					new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(10000));
		} catch (PlaywrightException e) {
			System.out.println("⚠️ Loader did not disappear within timeout.");
		}
	}
}
