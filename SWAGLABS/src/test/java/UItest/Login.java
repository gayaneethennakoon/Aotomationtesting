package UItest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.time.Duration;

public class Login {
    //Global variable section
    String BaseURL = "https://www.saucedemo.com/v1/index.html";
    WebDriver driver;

    String username_txt;
    String password_txt;

    @BeforeTest
    public void BeforeTestMethod(){
        //Assign browser value to the web driver object
        driver = new ChromeDriver();
        //Open the Chrome browser window and maximize in the UI
        driver.manage().window().maximize();
        //Through the driver object, calling the URL
        driver.get(BaseURL);
    }

    @Test(priority = 1)
    public void GetPageTitle() throws InterruptedException{
        Thread.sleep(3000);
        //Verify page Title
        System.out.println("The title of the facebook page is : "+driver.getTitle());

    }
    @Test(priority = 2)
public void testLogoimage(){
        // Verify Logo image
    WebElement element = driver.findElement(By.className("login_logo"));
    String bgImage = element.getCssValue("background-image");
    String actualUrl = bgImage.replace("url(\"", "").replace("\")", "").replace("url(", "").replace(")", "");
    String expectedUrl = "https://www.saucedemo.com/v1/img/SwagLabs_logo.png";
    System.out.println("Logo Image :"+ actualUrl);
    // Verify the extracted URL
    Assert.assertEquals(actualUrl, expectedUrl, "Logo image URL is incorrect!");
}
@Test (priority = 3)
public void testBodyImage(){
    //Verify body image
    WebElement bodyelement = driver.findElement(By.className("bot_column"));
    String bodyImage = bodyelement.getCssValue("background-image");
    String bactualUrl = bodyImage.replace("url(\"", "").replace("\")", "").replace("url(", "").replace(")", "");
    String expectedbodyUrl = "https://www.saucedemo.com/v1/img/Login_Bot_graphic.png";
    System.out.println("Body Image :"+ bactualUrl);
    // Verify the extracted URL
    Assert.assertEquals(bactualUrl, expectedbodyUrl, "Background image URL is incorrect!");
}

@Test(priority = 4)
public void testPlaceholderText(){
      WebElement Username = driver.findElement(By.id("user-name"));
      WebElement password = driver.findElement(By.id("password"));
    // Get the placeholder attribute value
    String acuPlaceholder = Username.getAttribute("placeholder");
    String acpsPlaceholder = password.getAttribute("placeholder");

    // Define the expected placeholder text
    String exuPlaceholder = "Username";
    String expPlaceholder = "Password";

    System.out.println("Placeholder username : " +acuPlaceholder + " ---password : "+ acpsPlaceholder );
    // Verify that the placeholder text is correct
    Assert.assertEquals(acuPlaceholder, exuPlaceholder, " User name Placeholder text is incorrect!");
    Assert.assertEquals(acpsPlaceholder, expPlaceholder, "Password Placeholder text is incorrect!");
}
@Test(priority = 5)
    public void testNulltxt() throws InterruptedException{
    Thread.sleep(3000);
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        //Trigger the action
        username_txt = "";
        password_txt = "";
        username.sendKeys(username_txt);
        password.sendKeys(password_txt);
        loginBtn.click();
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/h3")).getText();
        String expectedErrorMessage = "Epic sadface: Username is required";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch!");

    }
    @Test(priority = 6)
    public void TestUsernameError(){
        //Locate/Identify web elements
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        //Trigger the action
        username_txt = "";
        password_txt = "secret_sauce";
        username.sendKeys(username_txt);
        password.sendKeys(password_txt);
        loginBtn.click();
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/h3")).getText();
        String expectedErrorMessage = "Epic sadface: Username is required";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch!");

    }
    @Test(priority = 7)
public void testloginError() throws InterruptedException{
        Thread.sleep(3000);
    WebElement username = driver.findElement(By.id("user-name"));
    WebElement password = driver.findElement(By.id("password"));
    WebElement loginBtn = driver.findElement(By.id("login-button"));
    //Trigger the action
        username.clear();
        // Select all text and delete it
        password.sendKeys(Keys.CONTROL + "a");
        password.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
    username_txt = "abc";
    password_txt = "abc";
    username.sendKeys(username_txt);
    password.sendKeys(password_txt);
    loginBtn.click();
    String actualErrorMessage = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/h3")).getText();
    String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";

    Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch!");

}
@Test(priority = 8)
public void testUserError() throws InterruptedException{
    Thread.sleep(3000);
    //Locate/Identify web elements
    WebElement username = driver.findElement(By.id("user-name"));
    WebElement password = driver.findElement(By.id("password"));
    WebElement loginBtn = driver.findElement(By.id("login-button"));

    //Clear username
    username.sendKeys(Keys.CONTROL+"a");
    username.sendKeys(Keys.BACK_SPACE);
    // clear password
    password.sendKeys(Keys.CONTROL + "a");
    password.sendKeys(Keys.BACK_SPACE);
    Thread.sleep(1000);
    username_txt = "abc";
    password_txt = "secret_sauce";
    username.sendKeys(username_txt);
    password.sendKeys(password_txt);
    loginBtn.click();
    String actualErrorMessage = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/h3")).getText();
    String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";

    Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch!");

}

@Test(priority = 9)
public void testPasswordError()throws InterruptedException{
    Thread.sleep(3000);
    //Locate/Identify web elements
    WebElement username = driver.findElement(By.id("user-name"));
    WebElement password = driver.findElement(By.id("password"));
    WebElement loginBtn = driver.findElement(By.id("login-button"));

    //username.clear();
    username.sendKeys(Keys.CONTROL+"a");
    username.sendKeys(Keys.BACK_SPACE);
    // Select all text and delete it
    password.sendKeys(Keys.CONTROL + "a");
    password.sendKeys(Keys.BACK_SPACE);
    Thread.sleep(1000);
    username_txt = "standard_user";
    password_txt = "abcvbn";
    username.sendKeys(username_txt);
    password.sendKeys(password_txt);
    loginBtn.click();
    String actualErrorMessage = "Epic sadface: Username and password do not match any user in this service";
    String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";

    Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch!");

}
    @Test(priority = 10)
    public void Loginhappy() throws InterruptedException{


        Thread.sleep(3000);
        //Locate/Identify web elements
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));

        //username.clear();
        username.sendKeys(Keys.CONTROL+"a");
        username.sendKeys(Keys.BACK_SPACE);
        // Select all text and delete it
        password.sendKeys(Keys.CONTROL + "a");
        password.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        username_txt = "standard_user";
        password_txt = "secret_sauce";
        username.sendKeys(username_txt);
        password.sendKeys(password_txt);
        loginBtn.click();
    }



    @AfterTest
    public void Aftertest() {
//driver.quit();
        System.out.println("Clearing test data after execution...");
        // Close connections, delete test files, or reset fields
    }




}
