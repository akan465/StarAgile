package SeleniumGrid;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class parallelExecution {
	public WebDriver driver;
	@Parameters({"bname"})
  @Test
  public void MultipleBrowserTest(String bname)throws InterruptedException, MalformedURLException {
		  System.out.println("remote driver connectivity completed");
	  if(bname.equals("Chrome")) {
		  ChromeOptions options = new ChromeOptions();
		 driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
		 System.out.println("Session created on chrome!");
	  }
	  else if(bname.equals("Firefox")) {
		  FirefoxOptions options = new FirefoxOptions();
			 driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
			 System.out.println("Session created on Firefox!");
	  }
	  else if (bname.equals("Edge")) {
		  EdgeOptions options = new EdgeOptions();
			 driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
			 System.out.println("Session created on Edge!");
	  }
	  Thread.sleep(10000);
	  driver.get("https://www.amazon.in");
	  Thread.sleep(6000);
	  System.out.println("Title of the page is :" +driver.getTitle());
	  driver.quit();
	  
  }
}
