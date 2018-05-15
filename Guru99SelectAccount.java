//Page Object Model to select the account to Update or Delete.
package pageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Guru99SelectAccount {
    /**
     * All WebElements are identified by @FindBy annotation
     */
    WebDriver driver;
    @FindBy(name="accountno") WebElement accountId;
    @FindBy(linkText="Edit Account") WebElement editAccountLink;
    @FindBy(linkText="Delete Account") WebElement deleteAccountLink;
    @FindBy(name="AccSubmit") WebElement submitBtn;
                       
    public Guru99SelectAccount(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    //Click on Edit Customer Link to go to edit customer.
    public void clickEditAccountLink(){
       	editAccountLink.click();
    }      

    //Click on Delete Customer Link to go to delete customer.
    public void clickDeleteAccountLink(){
       	deleteAccountLink.click();
    }      
    
    //Set account Id in textbox
    public void setAccountId(String strAccountId){
    	accountId.sendKeys(strAccountId);       
    }

    //Click on Submit button
    public void clickSubmit(){
    	submitBtn.click();
    }
    
    /**
     * This POM method will be exposed in test case to login in the application
     * @param strCustomerId
     * */

    public void selectAccount(String strAccountId){ 	
        //Fill customer Id
        this.setAccountId(strAccountId);
                 
        //Click Submit button
        this.clickSubmit();               
    }
}