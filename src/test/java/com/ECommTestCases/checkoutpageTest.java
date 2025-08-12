package com.ECommTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.E_CommPages.CartPage;
import com.E_CommPages.CheckOutPage;
import com.E_CommPages.LoginPage;

public class checkoutpageTest extends BaseClass {
	
	LoginPage lp;
	CartPage cp;
	CheckOutPage chk;
	
	@BeforeMethod
	public void setUpPages() throws InterruptedException {
	    lp = new LoginPage(driver);
	    cp = new CartPage(driver);
	    chk = new CheckOutPage(driver);
	    // Login before every test
	    lp.validCred("standard_user", "secret_sauce");
	    cp.addToCarts();  
	    cp.removeItem();
	    cp.goToCart();

	}
	
	@Test
	public void checkbtn() {
		chk.checkoutb();
		chk.validInfo("Sherlyn", "Marco", "48110");
		chk.finishProcess();
		chk.verifyOrderSuccessMessage();
	}


}
