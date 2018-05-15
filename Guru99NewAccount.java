
package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Guru99NewAccount {
    /**
     * All WebElements are identified by @FindBy annotation
     */
    WebDriver driver;
    @FindBy(linkText="New Account") WebElement newAccountLink;
    
   // @FindBy(xpath="//a[@href='EditCustomer.php']") WebElement editCustomerLink;
    @FindBy(name="cusid") WebElement customerId;
    @FindBy(name="selaccount") WebElement accountTypeBox;
    @FindBy(name="inideposit") WebElement intitalDeposit;
    @FindBy(name="button2") WebElement submitBtn;
    
    // Below fields are from the confirmation page displayed after created the account.
    @FindBy(xpath="//*[@id=\"account\"]/tbody/tr[1]/td/p") WebElement confirmationMsg;   
    @FindBy(xpath="//*[@id=\"account\"]/tbody/tr[4]/td[2]") WebElement accountId;
                   
    public Guru99NewAccount(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    //Click on New Acount Link to go to Create new account.
    public void clickNewAccountLink() {
    	newAccountLink.click();
/*    	
    	System.out.println("******** Going to click on the new account page ****");
    	List<WebElement> hectorLinkElements = driver.findElements(By.tagName("a")); // Create a list of WebElements containing all the LINK elements in the page.
    	System.out.println("***hectorLinkElements.size(): "+hectorLinkElements.size());
    	for (int j = 0; j < hectorLinkElements.size(); j++) {
        	WebElement  ws_element = driver.findElements(By.tagName("a")).get(j); 
        	//assign the linkElement(j) to the ws_element WebElement.
        	String linkText = ws_element.getText();   
        	System.out.println("***linkText: "+linkText);
        	if (linkText.equals("New Account")) { 
        	   System.out.println("\"" + linkText + "\"" + " ******** link to Test."); 
        	   ws_element.click();  // click on the link save in the element webElement.
        	   System.out.println("---**** url: " + driver.getCurrentUrl());
        	   System.out.println(linkText+": "+ "\""+driver.getTitle()+"\"" );
        	   return;
        	}
        }
 */   	
    }      
    
    //Set customer Id in textbox
    public void setCustomerId(String strCustomerId){
    	customerId.sendKeys(strCustomerId);       
    }

    //Set account type listbox
    public void setAccountType(String strAccountType) {         
		// select the account type from list box field: Savings/Current
		Select accTypeListBox = new Select(accountTypeBox);
		accTypeListBox.selectByValue(strAccountType);  	    	
    }

    //Set initial deposit textbox
    public void setInitialDeposit(String strInitialDeposit){
    	intitalDeposit.sendKeys(strInitialDeposit);       
    }  
    
    //Click on Submit button
    public void clickSubmit(){
    	submitBtn.click();
    }
 
    // Below methods are for fields in the page returned after the account is created.
    //Get the confirmation Message New account Creation
    public String getConfirmationMessage(){
       return confirmationMsg.getText();
    }
    
   //Get the created accountId
    public String getAccountId(){
       return accountId.getText();
    }
    
    /**
     * This POM method will be exposed in test case to login in the application
     * @param strCustomerId
     * */

    public void newAccount(String strCustomerId, String strAccountType, String strInitialDeposit ){ 	
        //Fill customer Id
        this.setCustomerId(strCustomerId);
        //Fill account type
        this.setAccountType(strAccountType);
        //Fill initial deposit
        this.setInitialDeposit(strInitialDeposit);
            
        //Click Submit button
        this.clickSubmit();               
    }
}