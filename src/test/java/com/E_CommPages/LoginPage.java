	package com.E_CommPages;
	
	import java.time.Duration;
	
	import org.hamcrest.text.IsEmptyString;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	
	public class LoginPage {
		
		//encapsulation =private data + public function
		
		private WebDriver driver;
		
	    private WebDriverWait wait;
		
		public LoginPage(WebDriver driver) {
			
			this.driver=driver;
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
		}
		
		private By uname = By.xpath("//input[@id='user-name']");
		private By Pswd = By.id("password");
		private By LoginBtn = By.id("login-button");
		private By logout = By.xpath("//a[text()='Logout']");
		private By lpanel = By.xpath("//button[text()='Open Menu']");
		public void validCred(String un, String Psswd) {
			WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(uname));
			username.sendKeys(un);
			driver.findElement(Pswd).sendKeys(Psswd);
			driver.findElement(LoginBtn).click();
			
			String pagetitle = driver.getTitle();
	        Assert.assertTrue(pagetitle.contains("Swag"), "Login failed with valid credentials");
	        System.out.println("Test case with Valid login Passed");
			//driver.findElement(lpanel).click();
			//driver.findElement(logout).click();
		}
		public void invalidcred(String un1, String pw1) {
			driver.findElement(uname).sendKeys(un1);
			driver.findElement(Pswd).sendKeys(pw1);
			driver.findElement(LoginBtn).click();
			
		    String ErrMsg = driver.findElement(By.cssSelector("[data-test='error']")).getText();
			Assert.assertTrue(ErrMsg.contains("Epic"),"Error message not displayed");
			System.out.println("Testcase with invalid credentials is passed");
	        //driver.quit();
		}
		
		public void EmptyCred() throws InterruptedException {
			driver.findElement(uname).sendKeys(" ");
			driver.findElement(Pswd).sendKeys(" ");
			driver.findElement(LoginBtn).click();
			Thread.sleep(2000);
			WebElement error = driver.findElement(By.cssSelector("[data-test='error']")); // or actual locator
		    Assert.assertTrue(error.isDisplayed(), "Error message not displayed for empty credentials");
			System.out.println("Testcase with Empty credentials is passed");
	        //driver.quit();	
		}
		
	}
