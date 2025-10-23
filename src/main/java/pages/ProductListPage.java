package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;

public class ProductListPage {
	Page page;

	private Locator addToCartBtn;
	private Locator productTitle;
	private String loader;

	public ProductListPage(Page page) {
		this.page = page;
		initLocator();
	}

	private void initLocator() {
		addToCartBtn = page.locator("//div[contains(@class, 'vertical-card_action-container')]//button[not(contains(@class, 'Mui-disabled'))]");
		productTitle = page.locator("//div[contains(@class, 'vertical-card_title-container')]/div");
		loader = "//div[contains(@class,'loader_loader__lds-ellipsis')]";
	}

	public void waitForLoaderToDisappear() {
		try {
			page.waitForSelector(loader, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(10000));
		} catch (PlaywrightException e) {
			System.out.println("Loader did not disappear within timeout.");
		}
	}

	public void clickOnAddToCartButton(String value) {
		int count = productTitle.count();
		if (count > 0) {
			for (int i = 0; i < count - 1; i++) {
				if (productTitle.nth(i).textContent().trim().contains(value.trim())) {
					addToCartBtn.nth(i).scrollIntoViewIfNeeded();
					addToCartBtn.nth(i).click();
					break;
				}
			}
		} else {
			clickOnAddToCartButton(value);
		}
		waitForLoaderToDisappear();
	}
}
