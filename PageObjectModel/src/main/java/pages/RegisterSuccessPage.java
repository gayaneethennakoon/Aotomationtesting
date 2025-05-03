package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterSuccessPage {

    WebDriver driver;
    public RegisterSuccessPage(WebDriver driver){
        this.driver = driver;
    }

By Successtext_loc = By.xpath("//b[contains(text(),'Dear')]");

public String RegisterSuccesstext(){
    String Successtext =driver.findElement(Successtext_loc).getText();
    return Successtext;
}

}
