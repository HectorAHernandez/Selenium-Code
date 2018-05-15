package newTestNGPackage;   //Version 1.0.0    April 15 2015 Hector Hernandez.
// This is part of my practice for learning automation using Selenium Webdriver.
// code is saved in C:\Eclipse\work_space\newproject\
	import org.testng.annotations.*;
	import org.testng.Assert;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
//    import org.openqa.selenium.firefox.FirefoxDriver;

    import java.util.List;
    import java.util.concurrent.TimeUnit;

//	import org.openqa.selenium.firefox.FirefoxDriver;
//	import org.openqa.selenium.*;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.By;
	import org.openqa.selenium.support.ui.Select;
	
// Version 1.1
/*	In this version adding Test for:
    1- Page Select a Flight: Selected Depart and Return flight and Date.
    2- Page Book a Flight:  Selected Depart and Return flight and Date
                            Departure: Airline, Class and Price 
                            Return: Airline, Class and Price 
                            taxes and total price
    3- Page Flight confirmation: Confirmation message.
                           Selected Depart and Return flight and Date
                            Departure: Class and Price 
                            Return: Class and Price 
                            taxes and total price*/ 

	/* version 0.0 implementation: This is a Selenium Webdriver automated test using testNG. The objective is to test 
	java application newtours.demoaut.com executing below tests:
	- TestHomePageTitle.
	- TestFindAFlightPage.
	- TestSelectAFlightPage.
	- TestBookAFlightPage.
	- TestFlightConfirmationPage
	- Displays the Test results as an HTML file in the web browser.
*/ 
	
public class DemoToursTestNG_1_1 {
//	public String baseUrl = "http://demo.guru99.com/selenium/newtours/";
    public String baseUrl = "http://newtours.demoaut.com/";
	public WebDriver driver;
	public String expected = null;
	public String actual= null;
			  	
		
	  @BeforeTest  //this first test case, will execute before the Test.
	  public void launchBrowser() {
		  System.out.println("Launching the browser");
		  System.setProperty("webdriver.gecko.driver","C:\\selenium-3.11.0\\geckodriver.exe");
		  System.setProperty("webdriver.chrome.driver", "C:\\selenium-3.11.0\\chromedriver.exe");  
//  	      driver = new FirefoxDriver();
		  driver = new ChromeDriver();  
          driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // Define the time to wait, 5 seconds, after clicking the link.

		  driver.get(baseUrl);	  
	  }  
	  
	  @Test (priority = 0)   //below method validate the home page title and login to the application)
	  public void T00_TestAllLinksInHomePage() {

		    List<WebElement> hectorLinkElements = driver.findElements(By.tagName("a")); // Create a list of WebElements containing all the LINK elements in the page.
	        String[] array_linkTexts = new String[hectorLinkElements.size()]; // Create an String array, linkTexts, the size of the LINK elements list.
	        
	        // Create array with the name of all the links in the page.
	        String[] arrayLinkName = new String[] {"Home", "Flights", "Hotels", "Car Rentals", "Cruises", "Destinations",
	        	"Vacations", "SIGN-ON", "REGISTER", "SUPPORT", "CONTACT", "your destination",
	        	"featured vacation destinations", "Register here", "Business Travel @ About.com",
	        	"Salon Travel"};
	        
	        // Create array with the name of all the Pages in the page.
	        String[] arrayPageName = new String[] {"Welcome: Mercury Tours", "Welcome: Mercury Tours", "Under Construction: Mercury Tours",
	        	 "Under Construction: Mercury Tours", "Cruises: Mercury Tours", "Under Construction: Mercury Tours",
	        	 "Under Construction: Mercury Tours", "Sign-on: Mercury Tours",  "Register: Mercury Tours",
	        	 "Under Construction: Mercury Tours", "Under Construction: Mercury Tours",
	        	 "Under Construction: Mercury Tours", "Under Construction: Mercury Tours",
	        	"Register: Mercury Tours",  "404 Not Found",  "Salon: in-depth news, politics, business, technology & culture"};
	        
	        	     
	        int i = 0;
	        //extract the link texts of each link element
	        for (WebElement e : hectorLinkElements) {   
	        	//this is a foreach structure that define "e" a WebElement and in the loop 
	        	//access all the elements in the list until end.
	            array_linkTexts[i] = e.getText(); // assign the text of each link element to the array.
	            i++;
	        }

	       //list all the link moved
	       // for (String ws_link : array_linkTexts) {       // another foreach loop
	       //      System.out.println("\"" + ws_link + "\"" + " is one of the link.");
	       //      }   
	         
	       // for (int r = 0; r < arrayPageName.length; r++) {
	       //   	System.out.println(" Link Name: " + arrayLinkName[r] +"            And Page Name: " + arrayPageName[r]  );
	       //   }
	        
	        for (int j = 0; j < hectorLinkElements.size(); j++) {
	        	WebElement  ws_element = driver.findElements(By.tagName("a")).get(j); 
	        	//assign the linkElement(j) to the ws_element WebElement.
	        	String linkText = ws_element.getText();   
	        	//copy the text of the ws_element to the linkText string varialbe
	        	//System.out.println("\"" + linkText + "\"" + " ******** link to Test."); 
	        	// to see the link before the error on "Register here"
	        	ws_element.click();  // click on the link save in the element webElement.
	        	String currentPageTitle = driver.getTitle();
	        	String expectedPage = "NONE";
	        	actual =   "ALL Page Title Match the link clicked";
	        	expected = "ALL Page Title Match the link clicked";
	        	boolean found = false;
	        	
	        	for (int n = 0; n < arrayLinkName.length; n++) {
	                if (linkText.equals(arrayLinkName[n])) {
	                	if (currentPageTitle.equals(arrayPageName[n])) {
	            	       found = true; 
	            	       break;
	                	} else {
	                		expectedPage = arrayPageName[n];
	                	}
	                }  
	            }	        	
	        		        		  
	            if (!found) {
	            	actual = "** ONE Page Title DOES NOT Match the link clicked";	            	
	            	System.out.println("******* found ERROR: Not Found one page Title, found = "+found);
	            	System.out.println("currentPageTitle = "+ currentPageTitle + " *** Expected Page Title: " + expectedPage);
	            	break;
	            } 
	            driver.navigate().back();
	        }	  
	      System.out.println(" ---> ACTUAL: " + actual +" ---> EXPECTED: " + expected);
	      driver.get(baseUrl);	  
          Assert.assertEquals(actual, expected);          
	  }
	  
	  @Test (priority = 10)   //below method validate the home page title and login to the application)
	  public void T10_TestHomePageTitle() {
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
	  
	  @Test (priority = 20)
	  public void T20_TestFindAFlightPage() {
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
	  
	 
	  @Test (priority = 30)
	  public void T30_TestSelectAFlightPage() {
		// Validate the page title to make sure we are on the right page.
		  expected = "Select a Flight: Mercury Tours";
	      actual = driver.getTitle();
		  Assert.assertEquals(actual, expected);

		// Select Radio Button for the third Departure flights - Pangea.
		  driver.findElement(By.cssSelector("input[value=\"Pangea Airlines$362$274$9:17\"]")).click();
		// Select Radio Button for the third Returning flights - Pangea.
		  driver.findElement(By.cssSelector("input[value=\"Pangea Airlines$632$282$16:37\"]")).click();  
		  
	  }

//	    1- Page Select a Flight: Selected Depart and Return flight and Date.
	  @Test (priority = 31)
	  public void T31_TestSelectedDepartAndReturnCitiesAndDate() {
		/* Validate the Depart and Return From and To cities and dates are the ones selected in 
		 * previous "Find a Flight" page */
		  expected = "ALL Displayed fields are the ones selected by customer";
	      actual = "ALL Displayed fields are the ones selected by customer";
	      boolean found = false;
	     
	      String expectedDepartFromToCities = "London to New York";
	      String currentDepartFromToCitiesText = driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[1]/tbody/tr[1]/td/table/tbody/tr[2]/td[1]/b/font")).getText();
	      if (currentDepartFromToCitiesText.equals(expectedDepartFromToCities)) {
          	  found = true; 
      	  } else {
      		  found = false;
              actual = "Depart From-To Cities displayed is NOT the ones selected by customer";	            	
              System.out.println("*** ERROR *** ---> Current Depart FromTo cities = "+ currentDepartFromToCitiesText 
            			+ " *** Expected ones = " + expectedDepartFromToCities);
            }          	
                        
  	      String expectedDepartDate = "3/20/2018";
  	      String currentDepartDateText = driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[1]/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/b/font")).getText();
  	      if (currentDepartDateText.equals(expectedDepartDate)) {
              found = true; 
          } else {
        	  found = false;
        	  actual = "Depart Date displayed Is NOT the ones selected by customer";	            	
              System.out.println("*** ERROR *** ---> Current Depart date = "+ currentDepartDateText 
          			+ " *** Expected ones = " + expectedDepartDate);
        	}
  	    
	      String expectedReturnFromToCities = "New York to London";
	      String currentReturnFromToCitiesText = driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[2]/tbody/tr[1]/td/table/tbody/tr[2]/td[1]/b/font")).getText();
	      if (currentReturnFromToCitiesText.equals(expectedReturnFromToCities)) {
          	  found = true; 
      	  } else {
      		  found = false;
              actual = "Return From-To Cities displayed is NOT the ones selected by customer";	            	
              System.out.println("*** ERROR *** ---> Current Return FromTo cities = "+ currentReturnFromToCitiesText 
            			+ " *** Expected ones = " + expectedReturnFromToCities);
            }          	
  	      
  	      String expectedReturnDate = "4/30/2018";
  	      String currentReturnDateText = driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[2]/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/b/font")).getText();
  	      if (currentReturnDateText.equals(expectedReturnDate)) {
              found = true; 
          } else {
        	  found = false;
        	  actual = "Return Date displayed Is NOT the ones selected by customer";	            	
              System.out.println("*** ERROR *** ---> Current Return date = "+ currentReturnDateText 
          			+ " *** Expected ones = " + expectedReturnDate);
        	}  	 
  	     	      
	      
          Assert.assertEquals(actual, expected);
	      driver.findElement(By.name("reserveFlights")).click(); //click continue
	  }
	  
	  
	  @Test (priority = 40)
	  public void T40_TestBookAFlightPage() {
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
		
	  }

	  @Test (priority = 41)
	  public void T41_TestValidationOnBookAFlightPage() {
		// Validate the Depart and Return From and To cities and dates are the ones selected.
		//   Departure: Airline, Class and Price 
        //   Return: Airline, Class and Price 
        //   taxes and total price 
		//   from previous "select a Flight" page 
		  expected = "On Book a flight page: ALL Displayed fields are the ones selected by customer";
	      actual = "On Book a flight page: ALL Displayed fields are the ones selected by customer";
	      boolean found = false;
	      	     
	      String expectedDepartFromToCities = "London to New York";
	                                                                            
	      String currentDepartFromToCitiesText = driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[1]/td[1]/b/font")).getText();
	      if (currentDepartFromToCitiesText.equals(expectedDepartFromToCities)) {
          	  found = true; 
      	  } else {
      		  found = false;
              actual = "Depart From-To Cities displayed is NOT the ones selected by customer";	            	
              System.out.println("*** ERROR *** ---> Current Depart FromTo cities = "+ currentDepartFromToCitiesText 
            			+ " *** Expected ones = " + expectedDepartFromToCities);
            }          	
	                             
  	      String expectedDepartDate = "3/20/2018";
  	      String currentDepartDateText = driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[1]/td[2]/b/font")).getText();
  	      if (currentDepartDateText.equals(expectedDepartDate)) {
              found = true; 
          } else {
        	  found = false;
        	  actual = "Depart Date displayed Is NOT the ones selected by customer";	            	
              System.out.println("*** ERROR *** ---> Current Depart date = "+ currentDepartDateText 
          			+ " *** Expected ones = " + expectedDepartDate);
        	}
  	    	    
	      String expectedReturnFromToCities = "New York to London";
	      String currentReturnFromToCitiesText = driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[4]/td[1]/b/font")).getText();
	      if (currentReturnFromToCitiesText.equals(expectedReturnFromToCities)) {
          	  found = true; 
      	  } else {
      		  found = false;
              actual = "Return From-To Cities displayed is NOT the ones selected by customer";	            	
              System.out.println("*** ERROR *** ---> Current Return FromTo cities = "+ currentReturnFromToCitiesText 
            			+ " *** Expected ones = " + expectedReturnFromToCities);
            }          	
	      	      
  	      String expectedReturnDate = "4/30/2018";
  	      String currentReturnDateText = driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[4]/td[2]/b/font")).getText();
  	      if (currentReturnDateText.equals(expectedReturnDate)) {
              found = true; 
          } else {
        	  found = false;
        	  actual = "Return Date displayed Is NOT the ones selected by customer";	            	
              System.out.println("*** ERROR *** ---> Current Return date = "+ currentReturnDateText 
          			+ " *** Expected ones = " + expectedReturnDate);
        	}  	      	      

    	    String expectedDepartAirline = "Pangea Airlines 362";
  	      String expectedDepartAirlineText = driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[3]/td[1]/font/b")).getText();
  	      if (expectedDepartAirlineText.equals(expectedDepartAirline)) {
          	  found = true; 
      	  } else {
      		  found = false;
              actual = "Depart Airline displayed is NOT the ones selected by customer";	            	
              System.out.println("*** ERROR *** ---> Current Depart Airline = "+ expectedDepartAirlineText 
            			+ " *** Expected ones = " + expectedDepartAirline);
            }
  	      
  	      String expectedDepartPrice = "274";
  	      String expectedDepartPriceText = driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[3]/td[3]/font")).getText();
  	      if (expectedDepartPriceText.equals(expectedDepartPrice)) {
          	  found = true; 
      	  } else {
      		  found = false;
              actual = "Depart Price displayed is NOT the ones selected by customer";	            	
              System.out.println("*** ERROR *** ---> Current Depart Price = "+ expectedDepartPriceText 
            			+ " *** Expected ones = " + expectedDepartPrice);
            }
  	       
  	      String expectedReturnAirline = "Pangea Airlines 632";
  	      String expectedReturnAirlineText = driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[6]/td[1]/font/font/font[1]/b")).getText();
  	      if (expectedReturnAirlineText.equals(expectedReturnAirline)) {
          	  found = true; 
      	  } else {
      		  found = false;
              actual = "Return Airline displayed is NOT the ones selected by customer";	            	
              System.out.println("*** ERROR *** ---> Current Return Airline = "+ expectedReturnAirlineText 
            			+ " *** Expected ones = " + expectedReturnAirline);
            }
  	            
  	      String expectedReturnPrice = "282";
  	      String expectedReturnPriceText = driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[6]/td[3]/font")).getText();
  	      if (expectedReturnPriceText.equals(expectedReturnPrice)) {
          	  found = true; 
      	  } else {
      		  found = false;
              actual = "Return Price displayed is NOT the ones selected by customer";	            	
              System.out.println("*** ERROR *** ---> Current Return Price = "+ expectedReturnPriceText 
            			+ " *** Expected ones = " + expectedReturnPrice);
            }

  	      
          Assert.assertEquals(actual, expected);
          driver.findElement(By.name("buyFlights")).click(); //click Secure purchase.
      
	  }	  
	  
	  @Test (priority = 50)
	  public void T50_TestFlightConfirmationPage() {
		// Validate the page title to make sure we are on the right page.
		  expected = "Flight Confirmation: Mercury Tours";
	      actual = driver.getTitle();
		  Assert.assertEquals(actual, expected);
	  }

	  @Test (priority = 51)
	  public void T51_TestFlightConfirmationMessage() {
		// Validate the Flight Confirmatin message.
		  expected = "flight Confimatin Msg IS EQUAL";
	      actual = "flight Confimatin Msg IS EQUAL";
		  boolean found = false;
	      	  
		  String expectedFlightconfirmationMsg = "Your itinerary has been booked!";
	      String expectedFlightconfirmationMsgText = driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/p/font/b/font[2]")).getText();
	      if (expectedFlightconfirmationMsgText.equals(expectedFlightconfirmationMsg)) {
      	     found = true; 
  	      } else {
  		     found = false;
             actual = "flight Confimatin Msg IS NOT EQUAL";	            	
             System.out.println("*** ERROR *** ---> Current Confirmation Msg = "+ expectedFlightconfirmationMsgText 
        			+ " *** Expected ones = " + expectedFlightconfirmationMsg);
          }
	      
	      Assert.assertEquals(actual, expected);  
	  }
	  
	  @AfterTest
	  public void terminateBrowser() {
	    //  driver.navigate().to("file:///C:/Eclipse/work_space/newproject/test-output/index.html");
	      //driver.close();
	       
	  }

}


