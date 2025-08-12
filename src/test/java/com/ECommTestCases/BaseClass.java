package com.ECommTestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.E_CommPages.LoginPage;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class BaseClass {
	
	protected WebDriver driver;
	public LoginPage lp;
    ChromeOptions options = new ChromeOptions();
	


	
	@BeforeMethod
	public void setUp() {
       // WebDriverManager.chromedriver().setup();

		options.addArguments("--incognito");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("prefs", java.util.Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false));
		driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		lp = new LoginPage(driver);
		
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		//driver.close();
		driver.quit();
		Thread.sleep(5000);
	}
}
