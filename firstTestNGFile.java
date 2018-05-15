package newTestNGPackage;

import org.testng.annotations.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;

public class firstTestNGFile{
	public String baseUrl = "http://demo.guru99.com/selenium/newtours/";
	String driverPath = "C:\\selenium-3.11.0\\geckodriver.exe";
	public WebDriver driver;
 		  	
@Test  //this first test case, will check if Mercury Tours' homepage title is correct. 
  public void verifyHomepageTitle() {
	  System.out.println("Launching the browser");
	  System.setProperty("webdriver.gecko.driver", driverPath);
	  System.setProperty("webdriver.chrome.driver", "C:\\selenium-3.11.0\\chromedriver.exe");  
      driver = new FirefoxDriver();
//	   driver = new ChromeDriver();
	   driver.get(baseUrl);
	   String expectedTitle = "Welcome: Mercury Tours";
	   String actualTitle = driver.getTitle();
	   Assert.assertEquals(actualTitle, expectedTitle);
	   driver.close();
  
  }
}
