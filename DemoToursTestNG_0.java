package newTestNGPackage;   //Version 1.0.0    April 15 2015 Hector Hernandez.
// This is part of my practice for learning automation using Selenium Webdriver.
// code is saved in C:\Eclipse\work_space\newproject\
	import org.testng.annotations.*;
	import org.testng.Assert;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
//	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.*;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.By;
	import org.openqa.selenium.support.ui.Select;

/* This is a Selenium Webdriver automated test using testNG. The objective is to test 
	java application newtours.demoaut.com executing below tests:
	- TestHomePageTitle.
	- TestFindAFlightPage.
	- TestSelectAFlightPage.
	- TestBookAFlightPage.
	- TestFlightConfirmationPage
	- Displays the Test results as an HTML file in the web browser.
*/ 
	
public class DemoToursTestNG_0 {
	//public String baseUrl = "http://demo.guru99.com/selenium/newtours/";
	public String baseUrl = "http://newtours.demoaut.com/";
	public String driverPath = "C:\\selenium-3.11.0\\geckodriver.exe";
	public WebDriver driver;
	public String expected = null;
	public String actual= null;
			  	
		
	  @BeforeTest  //this first test case, will execute before the Test.
	  public void launchBrowser() {
		  System.out.println("Launching the browser");
//		  System.setProperty("webdriver.gecko.driver", driverPath);
		  System.setProperty("webdriver.chrome.driver", "C:\\selenium-3.11.0\\chromedriver.exe");  
//	    driver = new FirefoxDriver();
		  driver = new ChromeDriver();
		  driver.get(baseUrl);	  
	  }  
	  
	  @Test (priority = 0)   //below method validate the home page title and login to the application)
	  public void T00_TestHomePageTitle() {
		  expected = "Welcome: Mercury Tours";
		  actual = driver.getTitle();
		  Assert.assertEquals(actual, expected);
		  // provision the username and password: 
			WebElement weUserName = driver.findElement(By.name("userName"));
			WebElement wePassWord = driver.findElement(By.name("password"));
	        if (weUserName.isEnabled()) {
	        	weUserName.sendKeys("tutorial");
	        	wePassWord.sendKeys("tutorial");
	        	driver.findElement(By.name("login")).click(); //click login
	        }	
	  }
	  
	  @Test (priority = 1)
	  public void T01_TestFindAFlightPage() {
		  //driver.findElement(By.linkText("REGISTER")).click();
		  // Validate the page title to make sure we are on the right page.
		  expected = "Find a Flight: Mercury Tours:";
	      actual = driver.getTitle();
		  Assert.assertEquals(actual, expected);
		  		  
		  //Provision the data in the page: 
		  // Select Radio Button for roundtrip
		  driver.findElement(By.cssSelector("input[value=roundtrip]")).click();
		  // select the dropdown field Number passanger:
		  Select drpPassanger = new Select(driver.findElement(By.name("passCount")));
		  drpPassanger.selectByValue("1");
		  // select the Departure from list box field:
		  Select drpFromPort = new Select(driver.findElement(By.name("fromPort")));
		  drpFromPort.selectByValue("London");
		  // select the from Month list box field.
		  Select drpFromMonth = new Select(driver.findElement(By.name("fromMonth")));
		  drpFromMonth.selectByIndex(2); //Index is expressed on x-1.
		  // select the from Day list box field.
		  Select drpFromDay = new Select(driver.findElement(By.name("fromDay")));
		  drpFromDay.selectByValue("20");
		  
		  // select the Arriving in list box field:
		  Select drpToPort = new Select(driver.findElement(By.name("toPort")));
		  drpToPort.selectByVisibleText("New York");
          // select the to Month list box field.
		  Select drpToMonth = new Select(driver.findElement(By.name("toMonth")));
		  drpToMonth.selectByIndex(3); //Index is expressed on x-1.
		  // select the to Day list box field.
		  Select drpToDay = new Select(driver.findElement(By.name("toDay")));
		  drpToDay.selectByValue("30");
			
		  // Select Radio Button for Service Class as  First Class
		  driver.findElement(By.cssSelector("input[value=First]")).click();
		  
		  // select the Airline in list box field:
		  Select drpAirline = new Select(driver.findElement(By.name("airline")));
		  drpAirline.selectByVisibleText("Pangea Airlines");
		  
		  driver.findElement(By.name("findFlights")).click(); //click continue

	  }  
	  
	 
	  @Test (priority = 2)
	  public void T02_TestSelectAFlightPage() {
		// Validate the page title to make sure we are on the right page.
		  expected = "Select a Flight: Mercury Tours";
	      actual = driver.getTitle();
		  Assert.assertEquals(actual, expected);

		// Select Radio Button for the third Departure flights - Pangea.
		  driver.findElement(By.cssSelector("input[value=\"Pangea Airlines$362$274$9:17\"]")).click();
		// Select Radio Button for the third Returning flights - Pangea.
		  driver.findElement(By.cssSelector("input[value=\"Pangea Airlines$632$282$16:37\"]")).click();  
		  
		  driver.findElement(By.name("reserveFlights")).click(); //click continue
	  }
	  
	  @Test (priority = 3)
	  public void T03_TestBookAFlightPage() {
		// Validate the page title to make sure we are on the right page.
		  expected = "Book a Flight: Mercury Tours";
	      actual = driver.getTitle();
		  Assert.assertEquals(actual, expected);

		// provision the First Name, Last name and Credit card number: 
		WebElement weFirstName = driver.findElement(By.name("passFirst0"));
		weFirstName.sendKeys("Hector");			
		WebElement weLastName = driver.findElement(By.name("passLast0"));
		weLastName.sendKeys("Amparo");				        
		WebElement weCreditCardNumber = driver.findElement(By.name("creditnumber"));
		weCreditCardNumber.sendKeys("1111222233334444");	
		// select the Credit Card Type by value "BA" = Visa.
		Select drpCCType = new Select(driver.findElement(By.name("creditCard")));
		drpCCType.selectByValue("BA");
		  
		// select the Month of Experation date, using the option or visible value:
		Select drpExpMonth = new Select(driver.findElement(By.name("cc_exp_dt_mn")));
		drpExpMonth.selectByVisibleText("12");
		// select the Year of Experation date, using the index 9 for 2008.
		Select drpExpYear = new Select(driver.findElement(By.name("cc_exp_dt_yr")));
		drpExpYear.selectByIndex(9);
		
		//clear and Provision Address, city, State, postal code.
		WebElement weBillAddress = driver.findElement(By.name("billAddress1"));
		weBillAddress.clear();
		weBillAddress.sendKeys("2541 Sunset Drive");	
		WebElement weBillCity = driver.findElement(By.name("billCity"));
		weBillCity.clear();
		weBillCity.sendKeys("Sunny Tampa");	
		WebElement weBillState = driver.findElement(By.name("billState"));
		weBillState.clear();
		weBillState.sendKeys("FL");	
		WebElement weBillZip = driver.findElement(By.name("billZip"));
		weBillZip.clear();
		weBillZip.sendKeys("33655");
		
		// Check the Ticket Less checkbox
		WebElement chkTicketLesst = driver.findElement(By.name("ticketLess"));
		chkTicketLesst.click();
		
		driver.findElement(By.name("buyFlights")).click(); //click Secure purchase.
	  }
	  
	  @Test (priority = 4)
	  public void T04_TestFlightConfirmationPage() {
		// Validate the page title to make sure we are on the right page.
		  expected = "Flight Confirmation: Mercury Tours";
	      actual = driver.getTitle();
		  Assert.assertEquals(actual, expected);
	  }
 	  
	  @AfterTest
	  public void terminateBrowser() {
	      driver.navigate().to("file:///C:/Eclipse/work_space/newproject/test-output/index.html");
	      //driver.close();
	      
	  }

}
