package com.E_CommPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CheckOutPage {
	
	WebDriver driver;
    private WebDriverWait wait;
    
	public CheckOutPage(WebDriver driver) {

	this.driver=driver;
	
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	}	
	
	By chkoutbtn = By.cssSelector("#checkout");
	By Fname = By.id("first-name");
	By Lname = By.id("last-name");
	By zip = By.id("postal-code");
	By cntn = By.id("continue");
	By fin = By.id("finish");
	By confirmationMsg = By.cssSelector("h2.complete-header");



   public void checkoutb() {
	    wait.until(ExpectedConditions.elementToBeClickable(chkoutbtn)).click();
	 
}
   public void validInfo(String firstname, String Lastname, String zipcode) {
	   wait.until(ExpectedConditions.elementToBeClickable(Fname)).sendKeys(firstname);
	    wait.until(ExpectedConditions.elementToBeClickable(Lname)).sendKeys(Lastname);
	    wait.until(ExpectedConditions.elementToBeClickable(zip)).sendKeys(zipcode);

   }
   public void finishProcess() {
	   wait.until(ExpectedConditions.elementToBeClickable(cntn)).click();
	   wait.until(ExpectedConditions.elementToBeClickable(fin)).click();
	  
   }
   public void verifyOrderSuccessMessage() {
	    String expectedMsg = "Thank you for your order!";
	    WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMsg));
	    String actualMsg = msg.getText().trim();
	    System.out.println(actualMsg);

	    Assert.assertEquals(actualMsg, expectedMsg, "Order success message mismatch!");
	}
	
}