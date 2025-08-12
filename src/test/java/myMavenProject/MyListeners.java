package myMavenProject;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.internal.annotations.IListeners;
import org.openqa.selenium.io.FileHandler;

public class MyListeners implements ITestListener {
	WebDriver driver = new ChromeDriver();
	
	public void onStart(ITestContext result) {
		System.out.println("Test started!!!");
	}

	public void onFinish(ITestContext result) {
		System.out.println("Test completed!!!");
	}

	public void onTestFailure(ITestResult result) {
		String filename = "Akanmylistener.JPEG";
		File scrsht = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(scrsht, new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file1 = new File(filename);
		
		System.out.println("Test case fail: " + result.getName());
		System.out.println(result.getThrowable());
		
	}
		
		
	
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test skipped: " + result.getName());
		System.out.println(result.getThrowable());
	}

	public void onTestStart(ITestResult result) {
		System.out.println("Test case started : " + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test case Pass: " + result.getName());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

			
	}

