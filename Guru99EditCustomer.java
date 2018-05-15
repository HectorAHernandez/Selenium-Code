// Page Object Model for Edit customer page.
package pageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Guru99EditCustomer {
    /**
     * All WebElements are identified by @FindBy annotation
     */
    WebDriver driver;
    // Note: fields: customerName, gender and dateOfBirth are Not updateable so they are
    // not included. if included then this error will be generated:
    // "org.openqa.selenium.InvalidElementStateException: invalid element state: Element is not 
    // currently interactable and may not be manipulated"
    
    @FindBy(name="addr") WebElement customerAddress;
    @FindBy(name="city") WebElement city;
    @FindBy(name="state") WebElement state;
    @FindBy(name="pinno") WebElement pinnumber;
    @FindBy(name="telephoneno") WebElement telephoneNumber;
    @FindBy(name="emailid") WebElement emailAddress;
    @FindBy(name="sub") WebElement submitBtn;
    
    // Below fields are from the confirmation page displayed after created the customer.
    @FindBy(xpath="//*[@id=\"customer\"]/tbody/tr[1]/td/p") WebElement confirmationMsg;
                  
    //@FindBy(xpath="//*[@id=\"customer\"]/tbody/tr[4]/td[2]") WebElement customerId;
  
    public Guru99EditCustomer(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    //Set customer address in textbox
    public void setCustomerAddress(String strCustomerAddr){
    	customerAddress.sendKeys(strCustomerAddr);       
    }

    //Set city in textbox
    public void setCity(String strCity){
    	city.clear();
    	city.sendKeys(strCity);       
    }    
    
    //Set state in textbox
    public void setState(String strState){
    	state.clear();
    	state.sendKeys(strState);       
    }    

    //Set pin number in textbox
    public void setPinNumber(String strPinNumber){
    	pinnumber.clear();
    	pinnumber.sendKeys(strPinNumber);       
    }    

    //Set telephone number in textbox
    public void setTelephoneNumber(String strTelephoneNumber){
    	telephoneNumber.clear();
    	telephoneNumber.sendKeys(strTelephoneNumber);       
    }    

    //Set email address in textbox
    public void setEmailAddress(String strEmailAddress){
    	emailAddress.clear();
    	emailAddress.sendKeys(strEmailAddress);       
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
      
    
}