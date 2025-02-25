package testcases;

import org.testng.annotations.Test;

import base.Base;
import pages.WhereShouldWeDeliver;

public class SelectLocation extends Base {

	WhereShouldWeDeliver whereShouldWeDeliver;

	@Test
	public void whereShouldWeDeliver() {
		whereShouldWeDeliver = new WhereShouldWeDeliver();
		whereShouldWeDeliver.selectArea("380013", "Nava Vadaj, Ahmedabad");
	}

}
