//Page Object Model to select the customer to Update or Delete.
package pageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Guru99SelectCustomer {
    /**
     * All WebElements are identified by @FindBy annotation
     */
    WebDriver driver;
    @FindBy(name="cusid") WebElement customerId;
   // @FindBy(linkText="New Customer") WebElement newCustomerLink;
    
    //@FindBy(xpath="//html/body/div[2]/div/div/ul/li[3]/a") WebElement editCustomerLink;
   // @FindBy(xpath="//a[@href='EditCustomer.php']") WebElement editCustomerLink;
  
    @FindBy(linkText="Edit Customer") WebElement editCustomerLink;
    @FindBy(linkText="Delete Customer") WebElement deleteCustomerLink;
    @FindBy(name="AccSubmit") WebElement submitBtn;
                       
    public Guru99SelectCustomer(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    //Click on Edit Customer Link to go to edit customer.
    public void clickEditCustomerLink(){
       	editCustomerLink.click();
    }      

    //Click on Delete Customer Link to go to delete customer.
    public void clickDeleteCustomerLink(){
       	deleteCustomerLink.click();
    }      
    
    //Set customer Id in textbox
    public void setCustomerId(String strCustomerId){
    	customerId.sendKeys(strCustomerId);       
    }

    //Click on Submit button
    public void clickSubmit(){
    	submitBtn.click();
    }
    
    /**
     * This POM method will be exposed in test case to login in the application
     * @param strCustomerId
     * */

    public void selectCustomer(String strCustomerId){ 	
        //Fill customer Id
        this.setCustomerId(strCustomerId);
                 
        //Click Submit button
        this.clickSubmit();               
    }
}