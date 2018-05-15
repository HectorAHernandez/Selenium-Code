package newTestNGPackage;

//import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;  // to be used with the alert.
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageFactory.Guru99HomePage;
import pageFactory.Guru99Login;
import pageFactory.Guru99NewCustomer;
import pageFactory.Guru99SelectCustomer;
import pageFactory.Guru99EditCustomer;
import pageFactory.Guru99DeleteCustomer;
import pageFactory.Guru99NewAccount;
import pageFactory.Guru99SelectAccount;
import pageFactory.Guru99EditAccount;
import pageFactory.Guru99MakeDeposit;
import pageFactory.Guru99ValidateTransaction;


import java.util.Random;

public class UsingPOM_03_PageFactoryComplete {
    WebDriver driver;
    Guru99Login objLogin;
    Guru99HomePage objHomePage;  
    Guru99NewCustomer objNewCustomer;
    Guru99SelectCustomer objSelectCustomer;
    Guru99EditCustomer objEditCustomer;
    Guru99NewAccount objNewAccount;
    Guru99SelectAccount objSelectAccount;
    Guru99EditAccount objEditAccount;
    Guru99MakeDeposit objMakeDeposit;
    Guru99ValidateTransaction objValidateTransaction;
    public String expected = null;
	public String actual= null;
    String alertMessage = "";  
    String wsCustomerId = "";
    String wsAccountId = "";
    Random randomGen = new Random(); 
    String wsDepositAmount = null;
    String wsDecription = null;

    @BeforeTest
    public void setup(){
    	System.out.println("Launching the browser");
		System.setProperty("webdriver.gecko.driver","C:\\selenium-3.11.0\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\selenium-3.11.0\\chromedriver.exe");  
//	    driver = new FirefoxDriver();
		driver = new ChromeDriver();  
		        
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://demo.guru99.com/V4/");
        // note below maximize the browser this will avoid a lot of issue with menu on the
        // with options non clickable.
        driver.manage().window().maximize();
    }

    /**
     * This test case will login in http://demo.guru99.com/V4/
     * Verify login page title as guru99 bank
     * Login to application
     * Verify the home page using Dashboard message
     * Provision a new Customer.
     */

    @Test(priority=00)
    public void test00_Home_Page_Appear_Correct(){
    //Create Login Page object
    objLogin = new Guru99Login(driver);
    //Verify login page title
    actual = driver.getTitle().toLowerCase();
    expected = "guru99 bank home page";
    Assert.assertEquals(actual,expected);
    
    //login to application
    objLogin.loginToGuru99("mgr123", "mgr!23");
    
    // go the next page
    objHomePage = new Guru99HomePage(driver);
    //Verify home page
    actual = objHomePage.getHomePageDashboardUserName().toLowerCase();
    expected = "manger id : mgr123";
    Assert.assertEquals(actual,expected);
    }
    
    @Test(priority=10)
    public void test10_Create_New_Customer(){
    // create new customer object:
    objNewCustomer = new Guru99NewCustomer(driver); 
    // Go to create a new customer by clicking on "New customer" link
    objNewCustomer.clickNewCustomerLink();
    
    // Verify New customer page
    actual = driver.getTitle().toLowerCase();
    expected = "guru99 bank new customer entry page";
    Assert.assertEquals(actual,expected);
    
    // create random number to make a unique email address
	int randomSeq = randomGen.nextInt(50000);
	
    // Create new customer:
    objNewCustomer.createNewCustomer("Janice Smith","Female", "01/25/1970",
    		"4534 Sun Drive", "Sarasota", "Florida", "123456",
    		"7272547894", randomSeq+"roseGon@gmail.com", "password");
    
    // Save customer Id for the created customer
    wsCustomerId = objNewCustomer.getCustomerId();
    System.out.println("Saved customer Id: "+ wsCustomerId);
    
    //Verify New customer Confirmation Message
    actual = objNewCustomer.getConfirmationMessage().toLowerCase();
    expected = "customer registered successfully!!!";
    Assert.assertEquals(actual,expected);
    }
    
    @Test(priority=14)
    public void test14_EmailAddr_Already_Exist(){
   // create new customer object: *** NOT NEEDED BECAUSE IT WAS CREATED IN PREVIOUS TEST.
        objNewCustomer = new Guru99NewCustomer(driver); 
     // Go to create a new customer by clicking on "New customer" link
        objNewCustomer.clickNewCustomerLink();
    
    	// Create new customer with the same email address:
        objNewCustomer.createNewCustomer("Janice Smith","Female", "01/25/1970",
        		"4534 Sun Drive", "Sarasota", "Florida", "123456",
        		"7272547894", "8278roseGon@gmail.com", "password");       		
   
    	WebDriverWait hectorWaitVar = new WebDriverWait(driver, 10);
    	expected = "email address already exist !!";
    	actual = null;
        if (hectorWaitVar.until(ExpectedConditions.alertIsPresent()) != null) {
           actual = driver.switchTo().alert().getText().toLowerCase();
           driver.switchTo().alert().accept();
           System.out.println("The message in the Duplicate Email Alert is: "+actual);
        }
        Assert.assertEquals(actual,expected);
    } 
    
   /* Note if there is another field that needed to be validated in this same page, it
    * can be inserted below with another test with priority 15 and using the same above
    * code structure. */   

    // Edit customer Test
    @Test(priority=20)
    public void test20_Edit_Customer(){
       // create Select customer object:
       objSelectCustomer = new Guru99SelectCustomer(driver); 
    
     // Go to Edit customer by clicking on "Edit customer" link
       objSelectCustomer.clickEditCustomerLink();
   
    // Verify Select customer page
    actual = driver.getTitle().toLowerCase();
    expected = "guru99 bank edit customer page";
    Assert.assertEquals(actual,expected);
     
    // select the customer to Edit, using the wsCustomerId created in test "test_Create_New_Customer" 
    objSelectCustomer.selectCustomer(wsCustomerId);

    // create Edit customer object:
    objEditCustomer = new Guru99EditCustomer(driver); 

    // Verify Edit Customer Entry page
    actual = driver.getTitle().toLowerCase();
    expected = "guru99 bank edit customer entry page";
    Assert.assertEquals(actual,expected);

    //Change city
    objEditCustomer.setCity("Atlanta");
    //Change state
    objEditCustomer.setState("Georgia");
    //Change Pin number
    objEditCustomer.setPinNumber("654321");
    //Change TelephoneNumber
    objEditCustomer.setTelephoneNumber("4145237777");
    
    //Click Submit button
    objEditCustomer.clickSubmit(); 
      
    // Confirm Editing 
	
	//Verify Edit Customer Confirmation Message
    actual = objEditCustomer.getConfirmationMessage().toLowerCase();
    expected = "customer details updated successfully!!!";
    Assert.assertEquals(actual,expected);     

   }
    
    
    @Test(priority=30)
    public void test30_New_Account(){
       // create new account object:
       objNewAccount = new Guru99NewAccount(driver); 
       // Go to new account by clicking on "New Account" link
       objNewAccount.clickNewAccountLink();
       // Verify New Account page
       actual = driver.getTitle().toLowerCase();
       expected = "guru99 bank add new account";
       Assert.assertEquals(actual,expected);
     
       // Create new account for the customer created in test "test_Create_New_Customer" using the saved wsCustomerId
       objNewAccount.newAccount(wsCustomerId,"Current","125000000");

       // Save account Id for the created account
       wsAccountId = objNewAccount.getAccountId();
       System.out.println("Saved account Id: "+ wsAccountId);
       
       //Verify Confirmation Message
       actual = objNewAccount.getConfirmationMessage().toLowerCase();
       expected = "account generated successfully!!!";
       Assert.assertEquals(actual,expected);
       }
 
    // Edit account Test
    @Test(priority=40)
    public void test40_Edit_Account(){
       // create Select customer object:
       objSelectAccount = new Guru99SelectAccount(driver); 
    
       // Go to Edit Account by clicking on "Edit Account" link
       objSelectAccount.clickEditAccountLink();
   
       // Verify Select Account page
       actual = driver.getTitle().toLowerCase();
       expected = "guru99 edit account page";
       Assert.assertEquals(actual,expected);
     
       // select the account to Edit, using the wsAccountId created in test "test30_New_Account" 
       objSelectAccount.selectAccount(wsAccountId);

       // create Edit Account object:
       objEditAccount = new Guru99EditAccount(driver); 

       // Verify Edit Account Entry page
       actual = driver.getTitle().toLowerCase();
       expected = "guru99 bank edit account entry page";
       Assert.assertEquals(actual,expected);

       //Change Account type
       objEditAccount.setAccountType("Savings");
      
       //Click Submit button
       objEditAccount.clickSubmit(); 
      
       //Verify Edit account Confirmation Message
       actual = objEditAccount.getConfirmationMessage().toLowerCase();
       expected = "account details updated successfully!!!";
       Assert.assertEquals(actual,expected);     
   }
 
    @Test(priority=50)
    public void test50_Make_Deposit(){
       // create make Deposit object:
       objMakeDeposit = new Guru99MakeDeposit(driver); 
       // Go to make deposit by clicking on "Make deposit" link
       objMakeDeposit.clickMakeDepositLink();
       // Verify Make Deposit page
       actual = driver.getTitle().toLowerCase();
       expected = "guru99 bank amount deposit page";
       Assert.assertEquals(actual,expected);
     
       // Make the deposit for the account created in test "test30_New_Account" using the saved wsAccountId
       objMakeDeposit.makeDeposit(wsAccountId,"3500","Deposit 12");

       // Save depositAmount and description
       wsDepositAmount = objMakeDeposit.wsDepositAmount;
       wsDecription = objMakeDeposit.wsDecription;
       
       // Execute validate transaction
       actual = "all validations in transaction are valid";
       expected = "all validations in transaction are valid";
       if (!validateTransaction()) {
    	   actual = "SOME validation in transaction not valid, see log";
       }
       Assert.assertEquals(actual,expected);
     /*  
       //Verify Confirmation Message
       actual = objNewAccount.getConfirmationMessage().toLowerCase();
       expected = "account generated successfully!!!";
       Assert.assertEquals(actual,expected);
     */  
     }
    
/* 
    @Test(priority=80)
    public void test80_Delete_Customer(){
       // create delete customer object:
       objSelectCustomer = new Guru99DeleteCustomer(driver); 
    
     // Go to Delete customer by clicking on "Delete customer" link
       objSelectCustomer.clickDeleteCustomerLink();
   
    // Verify Delete customer page
    actual = driver.getTitle().toLowerCase();
    expected = "guru99 bank delete customer page";
    Assert.assertEquals(actual,expected);
     
    // select the customer to Delete, using the wsCustomerId created in test "test_Create_New_Customer" 
    objSelectCustomer.selectCustomer(wsCustomerId);
   
    // Confirm Deletion
	WebDriverWait hectorWaitVar = new WebDriverWait(driver, 10);
	if (hectorWaitVar.until(ExpectedConditions.alertIsPresent()) != null) {
    	driver.switchTo().alert().accept();
    }
 
    expected = "customer deleted successfully";
	actual = null;
    if (hectorWaitVar.until(ExpectedConditions.alertIsPresent()) != null) {
       actual = driver.switchTo().alert().getText().toLowerCase();
       driver.switchTo().alert().accept();
       System.out.println("The message in the Delete Alert is: "+ actual);
    }
  //Verify Deletion.
    Assert.assertEquals(actual,expected);

   }
*/

 public boolean validateTransaction() {
	 // create validate Transaction object:
     objValidateTransaction = new Guru99ValidateTransaction(driver); 
     if (!objValidateTransaction.getAccountId().equals(wsAccountId)) {
    	 System.out.println("**** Not equal Account Id in Deposit: " + 
    			 objValidateTransaction.getAccountId());
    	 return false;
     }
     if (!objValidateTransaction.getAmount().equals(wsDepositAmount)) {
    	 System.out.println("**** Not equal Deposit Amount in Deposit: " + 
    			 objValidateTransaction.getAmount());
    	 return false;
     }
     if (!objValidateTransaction.getDescription().equals(wsDecription)) {
    	 System.out.println("**** Not equal Description in Deposit: " + 
    			 objValidateTransaction.getDescription());
    	 return false;
     }
     if (!objValidateTransaction.getTransactionType().equals("Deposit")) {
    	 System.out.println("**** Not equal Transaction Type in Deposit: " + 
    			 objValidateTransaction.getTransactionType());
    	 return false;
     }
	 return true;
 }
    
    
}        
    

