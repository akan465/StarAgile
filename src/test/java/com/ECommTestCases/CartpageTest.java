package com.ECommTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.E_CommPages.CartPage;
import com.E_CommPages.LoginPage;

public class CartpageTest extends BaseClass{
	LoginPage lp;
	CartPage cp;
	
	@BeforeMethod
	public void setUpPages() {
		System.out.println("CartpageTest - setupPages called");
	    lp = new LoginPage(driver);
	    lp.validCred("standard_user", "secret_sauce");


	    cp = new CartPage(driver);

	    // Login before every test
	}
	@Test(priority = 1)
	public void addItems_CartItems_removal() throws InterruptedException {
		cp.addToCarts();
		cp.goToCart();
		cp.verifyCartCount("2");
		cp.removeItem();


	}
	
	
//	@Test(priority = 2)
//	public void removeproduct() {
//	}

}
