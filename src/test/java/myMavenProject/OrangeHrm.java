package myMavenProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.io.FileHandler;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class OrangeHrm extends HRMutil{

	WebDriver driver;
	Alert alt;
    ChromeOptions options = new ChromeOptions();
	
	File filename;
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet xsheet;
	XSSFRow rows;
	XSSFCell cells;
	int index = 0;
	ExtentReports extent;
	ExtentTest test;

	
	
	
	@BeforeTest
	  public void beforeTest() {


		options.addArguments("--incognito");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("prefs", java.util.Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
            ));
		  driver = new ChromeDriver(options);
		  driver.manage().window().maximize();
		  driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		  
		  filename = new File("D:\\Akanksha-MyWorkspace3\\myMavenProject\\src\\test\\resources\\Hrm.xlsx");
		  try {
			  
			fis = new FileInputStream(filename);//bcoz taking input from this file
			wb = new XSSFWorkbook(fis);
			xsheet = wb.getSheet("OrangeHRM");
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		  HRMutil util = new HRMutil();
			extent = util.reportsextent();

	  }
	

	@Test
	public void d() throws InterruptedException, IOException {
		  WebDriverWait Wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		  xsheet = wb.getSheetAt(0);
		  Thread.sleep(3000);
		for(int i= 1; i<=xsheet.getLastRowNum();i++) {

			rows = xsheet.getRow(i);
			
			String Uname = (rows.getCell(0) != null) ? rows.getCell(0).toString() : "";
	        String Pswd = (rows.getCell(1) != null) ? rows.getCell(1).toString() : "";
	        
	        test = extent.createTest("Login Test - User: " + Uname);
	        test.info("Trying login with username: " + Uname + " and password: " + Pswd);


	        Wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
	        driver.findElement(By.name("username")).clear();
	        driver.findElement(By.name("username")).sendKeys(Uname);
	        driver.findElement(By.name("password")).clear();
	        driver.findElement(By.name("password")).sendKeys(Pswd);
	        driver.findElement(By.xpath("//*[text()=' Login ']")).click();
	        
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        String screenshotpath = "D:\\Akanksha-MyWorkspace3\\myMavenProject\\screenshots";
            takeSS("screenshots/LoginAttempt_" + Uname + "_" + timestamp + ".png");
	        
	        

            if (Uname.equals("Admin") && Pswd.equals("admin123")) {
                boolean loggedIn = driver.getCurrentUrl().contains("dashboard");
                Assert.assertTrue(loggedIn, "Expected successful login for Admin.");
                System.out.println("Valid login passed for: " + Uname +" " +Pswd);
                
                
    			driver.findElement(By.xpath("//*[@href='/web/index.php/admin/viewAdminModule']")).click();//clicks on Admin
    			Thread.sleep(3000);
    			WebElement usernam = driver.findElement(By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']"));
    			usernam.click();
    			usernam.sendKeys("Admin");

                logout();
                
            } else {
            	
            }  driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            
            if(!Uname.equals("Admin") && !Pswd.equals("admin123")) {
            	System.out.println("Invalide attempt for :" +Uname+ " : " +Pswd );
            	
            } else {
            	
            }
		}
                
            
		}
         
		public void takeSS(String path) throws IOException, InterruptedException {
	        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        Thread.sleep(2000);
	        File dest = new File(path);
	        dest.getParentFile().mkdirs(); // Ensure folder exists
	        Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    }
		
		//public void clickAdmin() {
			
		//}
         
         
         public void logout() throws InterruptedException {
 	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
 	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-userdropdown-name")));
 	        driver.findElement(By.className("oxd-userdropdown-name")).click();
 	        Thread.sleep(3000);
 	        driver.findElement(By.xpath("//a[text()='Logout']")).click();
 	        Thread.sleep(1000);
 	    }
	
		
	@AfterTest
	  public void afterTest() throws IOException {
		  FileOutputStream FOS = new FileOutputStream(filename); //to write the result value
		  wb.write(FOS);
		  wb.close();
		  FOS.close();
		  fis.close();
		  driver.quit();
		  if (extent != null) {
		        extent.flush(); 
		        
		       

		  
		  
		  }  

}
}
