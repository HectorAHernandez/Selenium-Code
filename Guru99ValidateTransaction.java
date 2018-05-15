//Page Object Model to validate Transaction.
package pageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Guru99ValidateTransaction {
    /**
     * All WebElements are identified by @FindBy annotation
     */
    WebDriver driver;
    @FindBy(xpath="//*[@id=\"deposit\"]/tbody/tr[7]/td[2]") WebElement accountId;  
    @FindBy(xpath="//*[@id=\"deposit\"]/tbody/tr[12]/td[2]") WebElement amount;
    @FindBy(xpath="//*[@id=\"deposit\"]/tbody/tr[16]/td[2]") WebElement transactionType;
    @FindBy(xpath="//*[@id=\"deposit\"]/tbody/tr[20]/td[2]") WebElement description;
                           
    public Guru99ValidateTransaction(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
 
       //Get the Account id
    public String getAccountId(){
       return accountId.getText();
    }
 
    //Get the created Amount
    public String getAmount(){
       return amount.getText();
    }

    //Get the transaction type
    public String getTransactionType(){
       return transactionType.getText();
    }

  //Get the created Description
    public String getDescription(){
       return description.getText();
    }    
}