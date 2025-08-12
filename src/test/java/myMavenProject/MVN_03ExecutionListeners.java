package myMavenProject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(myMavenProject.MyListeners.class)
public class MVN_03ExecutionListeners {
	
	WebDriver driver;
	@Test
	public void test1() {
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//span[text()='Mobiles']")).click();
		Assert.assertTrue(true);
	}

	@Test
	public void test2() throws InterruptedException {
		//driver= new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//span[text()='Mobijnjles']")).click();
		Assert.assertTrue(false);
	}

	@Test
	public void test3() {
		throw new SkipException("Skiiping the testcases.");
	}


}
