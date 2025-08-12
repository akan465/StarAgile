package com.ECommTestCases;

import org.testng.annotations.Test;

import com.E_CommPages.LoginPage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class Loginpagetest extends BaseClass{
	//LoginPage lp;
	
	@BeforeMethod
	public void setup() {
	lp = new LoginPage(driver);
	}
	
	@Test(priority =1)
	public void validlogin() {
		lp.validCred("standard_user", "secret_sauce");
		
	}
	
	@Test(priority = 2)
	public void invalidlogin() {
		lp.invalidcred("abcde345", "abcde345");
		
   }
	
	@Test(priority = 3)
	public void blankvalue() throws InterruptedException {
		lp.EmptyCred();
		
	

	}
}
