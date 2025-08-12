package myMavenProject;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.io.FileHandler;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HRMutil {
			ExtentTest test;
			public static WebDriver driver;
			//ExtentTest test;
			public static void LaunchBrowser(){		
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");		
			}	
			public static void CloseBrowser() {		
				driver.quit();	
			}	
			
			
			
			public ExtentReports reportsextent() {
		        String reportPath = "D:\\Akanksha-MyWorkspace3\\myMavenProject\\reports\\HRMReport.html";
				ExtentReports report = new ExtentReports();

				ExtentSparkReporter htmlReport = new ExtentSparkReporter(reportPath);
				report = new ExtentReports();
				report.attachReporter(htmlReport);	
				report.setSystemInfo("Project Name", "HRM Automation Project");		
				htmlReport.config().setDocumentTitle("OrangeHRM Automation Report");	
				htmlReport.config().setReportName("TestNG Automation Report");
				htmlReport.config().setTheme(Theme.DARK);
				htmlReport.config().setTimeStampFormat("MM-DD-YYYY");			        
				//report.flush();
				return report;
			}

			public class ScreenshotUtil{
				WebDriver baseUtilDriver;
			public ScreenshotUtil(WebDriver driver) {
			        this.baseUtilDriver = driver;
			}
			
			
			public  class ExtentReporter {
			    public static final String screenshotFolderPath = "D:\\Akanksha-MyWorkspace3\\myMavenProject\\screenshots";
			}
			
			public void takeScreenShot(ExtentTest test){
				// fileName of the screenshot
				Date d=new Date();
				String screenshotFile=d.toString().replace(":", "").replace(" ", "")+".png";
				String filePath = ExtentReporter.screenshotFolderPath;
				// take screenshot
				File srcFile = ((TakesScreenshot)baseUtilDriver).getScreenshotAs(OutputType.FILE);
				try {
					// get the dynamic folder name
					FileUtils.copyFile(srcFile, new File(filePath+"//"+screenshotFile));
					//put screenshot file in reports
					test.log(Status.INFO,"Screenshot-> "+ test.addScreenCaptureFromPath(filePath+"//"+screenshotFile));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
			}

}