package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageUpdated {
    WebDriver driver;
    public HomePageUpdated(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
   //1st way
//    @FindBy(how = How.LINK_TEXT, using = "REGISTER")
//    public WebElement registerbut;
    //2nd way
@FindBy(linkText = "REGISTER")
public WebElement registerbut;



    public RegisterPageUpdated selectRegisterMenu(){
        registerbut.click();
        return new RegisterPageUpdated(driver);
    }
}
