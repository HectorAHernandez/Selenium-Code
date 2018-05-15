
package pageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Guru99NewCustomer {
    /**
     * All WebElements are identified by @FindBy annotation
     */
    WebDriver driver;
    @FindBy(linkText="New Customer") WebElement NewCustomerLink;
    @FindBy(name="name") WebElement customerName;
    @FindBy(xpath="//table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[1]") WebElement genderMale;
    @FindBy(xpath="//table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[2]") WebElement genderFemale;
    @FindBy(name="dob") WebElement dateOfBirth;
    @FindBy(name="addr") WebElement customerAddress;
    @FindBy(name="city") WebElement city;
    @FindBy(name="state") WebElement state;
    @FindBy(name="pinno") WebElement pinnumber;
    @FindBy(name="telephoneno") WebElement telephoneNumber;
    @FindBy(name="emailid") WebElement emailAddress;
    @FindBy(name="password") WebElement password;
    @FindBy(name="sub") WebElement submitBtn;
    
    // Below fields are from the confirmation page displayed after created the customer.
    @FindBy(xpath="//*[@id=\"customer\"]/tbody/tr[1]/td/p") WebElement confirmationMsg;
    @FindBy(xpath="//*[@id=\"customer\"]/tbody/tr[4]/td[2]") WebElement customerId;
  
    public Guru99NewCustomer(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    //Click on New Customer Link to go to create a new customer.
    public void clickNewCustomerLink(){
       	NewCustomerLink.click();
    }      
 
    //Set customer name in textbox
    public void setCustomerName(String strCustomerName){
    	customerName.sendKeys(strCustomerName);       
    }

    //Set Customer gender in Gender radio button
    public void setGender(String strGender){
    	if (strGender == "Male") {
    		genderMale.click();
    	}
    	else {
    		genderFemale.click();
    	}
    }

    //Set date of birth in date type 
    public void setDOB(String strDOB){
    	dateOfBirth.sendKeys(strDOB);       
    }

    //Set customer address in textbox
    public void setCustomerAddress(String strCustomerAddr){
    	customerAddress.sendKeys(strCustomerAddr);       
    }

    //Set city in textbox
    public void setCity(String strCity){
    	city.sendKeys(strCity);       
    }    
    
    //Set state in textbox
    public void setState(String strState){
    	state.sendKeys(strState);       
    }    

    //Set pin number in textbox
    public void setPinNumber(String strPinNumber){
    	pinnumber.sendKeys(strPinNumber);       
    }    

    //Set telephone number in textbox
    public void setTelephoneNumber(String strTelephoneNumber){
    	telephoneNumber.sendKeys(strTelephoneNumber);       
    }    

    //Set email address in textbox
    public void setEmailAddress(String strEmailAddress){
    	emailAddress.sendKeys(strEmailAddress);       
    }    

    //Set password in textbox
    public void setPassword(String strPassword){
    	password.sendKeys(strPassword);       
    }        
    
    //Click on Submit button
    public void clickSubmit(){
    	submitBtn.click();
    }

    // Below methods are for fields in the page returned after the customer is created.
    //Get the confirmation Message New Customer Creation
    public String getConfirmationMessage(){
       return confirmationMsg.getText();
    }
    
    //Get the created customerId
    public String getCustomerId(){
       return customerId.getText();
    }

    
    /**
     * This POM method will be exposed in test case to login in the application
     * @param strCustomerName
     * @param strGender ... strPassword
     * @return
     */

    public void createNewCustomer(String strCustomerName,String strGender, String strDateOfBirth,
    		String strCustomerAddress, String strCity, String strState, String strPinNumber,
    		String strTelephoneNumber, String strEmailAddress, String strPassword){
 	
        //Fill customer name
        this.setCustomerName(strCustomerName);
        //Fill Gender
        this.setGender(strGender);
      //Fill Date of Birth
        this.setDOB(strDateOfBirth);
      //Fill Customer Address
        this.setCustomerAddress(strCustomerAddress);
      //Fill city
        this.setCity(strCity);
      //Fill state
        this.setState(strState);
      //Fill Pin number
        this.setPinNumber(strPinNumber);
      //Fill TelephoneNumber
        this.setTelephoneNumber(strTelephoneNumber);
      //Fill Email Address
        this.setEmailAddress(strEmailAddress);
        //Fill password        
        this.setPassword(strPassword);
        
        //Click Submit button
        this.clickSubmit();               
    }
}