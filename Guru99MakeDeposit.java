//Page Object Model to Make a Deposit.
package pageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Guru99MakeDeposit {
    /**
     * All WebElements are identified by @FindBy annotation
     */
    WebDriver driver;
    @FindBy(name="accountno") WebElement accountId;
    //@FindBy(name="ammount") WebElement amount;
    @FindBy(xpath="/html/body/table/tbody/tr/td/table/tbody/tr[7]/td[2]/input") WebElement amount;
//    /html/body/table/tbody/tr/td/table/tbody/tr[7]/td[2]/input
    @FindBy(name="desc") WebElement description;
    @FindBy(linkText="Deposit") WebElement makeDepositLink;
    @FindBy(name="AccSubmit") WebElement submitBtn;
    
    // below two variable are save here to be used in the main program for validation, later 
    public String wsDepositAmount;
    public String wsDecription;
    
    public Guru99MakeDeposit(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    //Click on Edit Customer Link to go to edit customer.
    public void clickMakeDepositLink(){
    	makeDepositLink.click();
    }      
   
    //Set account Id in textbox
    public void setAccountId(String strAccountId){
    	accountId.sendKeys(strAccountId);       
    }

    //Set amount in textbox
    public void setAmount(String strAmount){
    	amount.sendKeys(strAmount);       
    }
    
    //Set description in textbox
    public void setDescription(String strDescription){
    	description.sendKeys(strDescription);       
    }

    //Click on Submit button
    public void clickSubmit(){
    	submitBtn.click();
    }
    
    //Get the created Amount
    public String getAmount(){
       return amount.getText();
    }
    
  //Get the created Description
    public String getDescription(){
       return description.getText();
    }
    
    /**
     * This POM method will be exposed in test case to login in the application
     * @param strCustomerId
     * */

    public void makeDeposit(String strAccountId, String strAmount, String strDescription){ 	
        //Fill customer Id
        this.setAccountId(strAccountId);           
        //Fill amount
        this.setAmount(strAmount);           
        //Fill Description.
        this.setDescription(strDescription);

        // Save depositAmount and description
        wsDepositAmount = strAmount;
        System.out.println("Saved deposit amount: "+ wsDepositAmount);
        wsDecription = strDescription;
        
        //Click Submit button
        this.clickSubmit();               
    }
}