package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    By RegisterBtn_loc =By.linkText("REGISTER");

    public void selectRegisterMenu(){
        driver.findElement(RegisterBtn_loc).click();
    }
}
