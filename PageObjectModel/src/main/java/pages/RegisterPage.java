package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
    WebDriver driver;
    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }



        By firstname_loc = By.xpath("//input[@name='firstName']");
        By lastname_loc = By.xpath("//input[@name='lastName']");

        By email_loc = By.xpath("//input[@id='email']");
        By country_loc = By.xpath("//select[@name='country']");
        By username_loc = By.xpath("//input[@id='userName']");
        By password_loc = By.xpath("//input[@name='password']");
        By confirmPassword_loc = By.xpath("//input[@name='confirmPassword']");
        By submitbtn_loc = By.xpath("//input[@name='submit']");

        public void setfirstname (String firstname){
            driver.findElement(firstname_loc).sendKeys(firstname);
        }
        public void setlastname (String lastname){
            driver.findElement(lastname_loc).sendKeys(lastname);
        }

        public void seteMail (String eMail){
            driver.findElement(email_loc).sendKeys(eMail);
        }
        public void selectCounrty(){
           WebElement countrySelect_ele = driver.findElement(country_loc);
            Select dropdowncountry = new Select(countrySelect_ele);
            dropdowncountry.selectByIndex(2);
        }
        public void setUsername(String UserName){
            driver.findElement(username_loc).sendKeys(UserName);
        }
        public void setPassword(String Password){
            driver.findElement(password_loc).sendKeys(Password);
        }
        public void setConfirmPassword(String ConfirmPassword){
            driver.findElement(confirmPassword_loc).sendKeys(ConfirmPassword);
        }
        public void submit(){
            driver.findElement(submitbtn_loc).click();
        }
}
