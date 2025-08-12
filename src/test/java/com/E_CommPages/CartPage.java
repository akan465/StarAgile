package com.E_CommPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.gson.annotations.Until;

public class CartPage {
    WebDriver driver;
    private WebDriverWait wait;
    
	public CartPage(WebDriver driver) {

	this.driver=driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	
	}	//
		By addtoCart = By.id("add-to-cart-sauce-labs-backpack");
		By addtoCart2 = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
		By carticon = By.xpath("//div[@id='shopping_cart_container']");
		By removeBtn = By.id("remove-sauce-labs-backpack");
		
	public void addToCarts() throws InterruptedException {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(addtoCart));
        wait.until(ExpectedConditions.elementToBeClickable(addtoCart)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addtoCart2)).click();
		
        System.out.println("Product 1 added");
        System.out.println("Product 2 added");

        Thread.sleep(3000);
	}
	
	public void goToCart() {
	    wait.until(ExpectedConditions.elementToBeClickable(carticon)).click();
	    System.out.println("Cart Launched");
	}
	
	public void verifyCartCount(String expectedCount) {
        wait.until(ExpectedConditions.elementToBeClickable(carticon)).click();
        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(carticon));
        String actualCount = badge.getText().trim();

		Assert.assertEquals(actualCount, expectedCount, "Count is incorrect");
		System.out.println("Cart Count Verified");
	}	
	
	public void removeItem() {
        wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
        System.out.println("Item removed from Cart");
		
	}
	
}
