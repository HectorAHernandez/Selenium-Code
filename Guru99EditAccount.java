// Page Object Model for Edit account.
package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Guru99EditAccount {
    /**
     * All WebElements are identified by @FindBy annotation
     */
    WebDriver driver;
   // Note: the balance is not updateable only the account type.
      
    @FindBy(name="a_type") WebElement accountTypeBox;
    @FindBy(name="AccSubmit") WebElement submitBtn;
    
    // Below fields are from the confirmation page displayed after updating the account.
    //@FindBy(xpath="//*[@id=\"customer\"]/tbody/tr[1]/td/p") WebElement confirmationMsg;   
    @FindBy(xpath="//*[@id=\"account\"]/tbody/tr[1]/td/p") WebElement confirmationMsg;
    //               //*[@id="customer"]/tbody/tr[1]/td/p
    //                "//*[@id="customer"]/tbody/tr[1]/td/p"
    public Guru99EditAccount(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    //Set account type listbox
    public void setAccountType(String strAccountType) {         
		// select the account type from list box field: Savings/Current
		Select accTypeListBox = new Select(accountTypeBox);
		accTypeListBox.selectByValue(strAccountType);  	    	
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
}