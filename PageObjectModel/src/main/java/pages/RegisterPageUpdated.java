package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegisterPageUpdated {
    WebDriver driver;
    public RegisterPageUpdated(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@name='firstName']")
    WebElement firstname_ele;

    @FindBy(xpath ="//input[@name='lastName']")
    WebElement lastname_ele;

    @FindBy(xpath="//input[@id='email']")
    WebElement email_ele;

    @FindBy(xpath="//select[@name='country']")
    WebElement country_ele;

    @FindBy(xpath = "//input[@id='userName']")
    WebElement username_ele;

    @FindBy(xpath = "//input[@name='password']")
    WebElement password_ele;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    WebElement confirmpassword_ele;

    @FindBy(xpath = "//input[@name='submit']")
     WebElement submitbut;


        public RegisterPageUpdated setfirstname (String firstname){

            firstname_ele.sendKeys(firstname);
            return this;
        }

        public RegisterPageUpdated setlastname (String lastname){

            lastname_ele.sendKeys(lastname);
            return this;
        }

        public RegisterPageUpdated seteMail (String eMail){

            email_ele.sendKeys(eMail);
            return this;
        }
        public RegisterPageUpdated selectCounrty(){

            Select dropdowncountry = new Select(country_ele);
            dropdowncountry.selectByIndex(2);
            return this;
        }
        public RegisterPageUpdated setUsername(String UserName){

            username_ele.sendKeys(UserName);
            return this;
        }
        public RegisterPageUpdated setPassword(String Password){

            password_ele.sendKeys(Password);
            return this;
        }
        public RegisterPageUpdated setConfirmPassword(String ConfirmPassword){
            confirmpassword_ele.sendKeys(ConfirmPassword);
            return this;
        }
        public RegisterSuccessPageUpdated submit(){

            submitbut.click();
            return new RegisterSuccessPageUpdated(driver);
        }
}
