package com.WebdriverDemo3;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HrmScenario_2 {
	
	static @FindBy(name  = "username")WebElement loginPage_uname;
	static @FindBy(name  = "password")WebElement loginPage_pass;
	static @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")WebElement loginPage_submit;
	static @FindBy(xpath = "//*[@href='/web/index.php/admin/viewAdminModule']")WebElement admin_option1;
	static @FindBy(xpath = "//ul[@class='oxd-main-menu']/li")List<WebElement> admin_leftSideMenuItems;
	static @FindBy(xpath = "//span[text()='Admin']")WebElement admin_MenuOption;
	static @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")WebElement searchByUname_SearchBox;
	static @FindBy(xpath = "//button[normalize-space()='Search']")WebElement searchByUname_searchBtn;
	static @FindBy(xpath = "//div[@class='oxd-table-body']/div")List<WebElement> resultRows;
	static @FindBy(xpath = "//div[@class='oxd-table-filter-area']//div[2]//div[1]//div[2]//div[1]//div[1]//div[2]//i[1]")WebElement searchbyRole_userRoleDropdown;
	static @FindBy(xpath = "//div[@class='oxd-select-option']/span[text()='Admin']")WebElement searchbyRole_adminOption;
	static @FindBy(xpath = "//button[normalize-space()='Reset']")WebElement resetButton;
	static @FindBy(xpath = "//label[text()='Status']/../following-sibling::div//div[@class='oxd-select-text-input']")WebElement searchByStatus_dropdown;
	static @FindBy(xpath = "//div[text()='Enabled']")WebElement searchByStatus_enabled;
//using PageFactory
	static WebDriver driver;


	public static void main(String[] args) {
		driver= new ChromeDriver();
		PageFactory.initElements(driver,HrmScenario_2.class );
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		loginPage();
		admin();
		searchByUname("Admin");
		searchbyRole();
		searchByStatus("Enabled");
		driver.quit();

	}
	
	public static void loginPage() {
		loginPage_uname.sendKeys("Admin");
		loginPage_pass.sendKeys("admin123");
		loginPage_submit.click();

		
	}
	
	public static void admin() {
		System.out.println("--------------------Side Menu Items-------------------");
        for (WebElement item : admin_leftSideMenuItems) {
            System.out.println(item.getText());
        }
        System.out.println("Total number of side menu options: " + admin_leftSideMenuItems.size());

        admin_MenuOption.click(); 
        admin_option1.click();
        
        WebElement userManagementHeader = driver.findElement(By.xpath("//*[@href='/web/index.php/admin/viewAdminModule']"));
        Assert.assertTrue(userManagementHeader.isDisplayed(), "Navigation to User Management failed.");
        System.out.println("------------------------------Assertion Passed for Admin TestCase -------------------------------------- ");

		
	}
	public static void searchByUname(String uname) {
		searchByUname_SearchBox.sendKeys("Admin");
		searchByUname_searchBtn.click();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfAllElements(resultRows));
		System.out.println("Search by Username '" + uname + "' found: " + resultRows.size() + " record(s).");
	    Assert.assertTrue(resultRows.size() > 0, "No results found for username: " + uname);
        System.out.println("------------------------------Assertion Passed for SearchByUsername TestCase -------------------------------------- ");

		//driver.close();

	}
	
	public static void searchbyRole() {
		searchbyRole_userRoleDropdown.click();
		searchbyRole_adminOption.click();
		searchByUname_searchBtn.click();
		System.out.println("Search by role,records found: " + resultRows.size() + " record(s).");
		
	    Assert.assertTrue(resultRows.size() > 0, "No results found for selected user role.");
        resetButton.click();
        System.out.println("Page refreshed");
        System.out.println("------------------------------Assertion Passed for SearchbyRole TestCase -------------------------------------- ");

        
	}
	
	public static void searchByStatus(String statusText) {
		searchByStatus_dropdown.click();
		searchByStatus_enabled.click();
		searchByUname_searchBtn.click();
        System.out.println("Search by Status '" + statusText + " found: " + resultRows.size() + " record(s).");
        resetButton.click();
        System.out.println("Page refreshed");
        Assert.assertTrue(resultRows.size() > 0, "No results found for status: " + statusText);
        System.out.println("------------------------------Assertion Passed for SearchbyStatus TestCase -------------------------------------- ");



    }

	
	

}
