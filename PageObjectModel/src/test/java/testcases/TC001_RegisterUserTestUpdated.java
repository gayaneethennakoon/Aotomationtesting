package testcases;

import functions.NewtoursCommonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class TC001_RegisterUserTestUpdated {
    WebDriver driver;

    @BeforeMethod
public void openPage(){
    driver =new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get("https://demo.guru99.com/test/newtours/index.php");
    }
@Test
    public void TC001(){
//Method 1
  //  registeruser();
  //  verifyRegistrationSucess();
    //method 2
//    NewtoursCommonFunctions newtoursCommonFunctions = new NewtoursCommonFunctions(driver);
//    String actualtext =newtoursCommonFunctions.registersuccessfunction("Gayanee","Thennakoon","testemail@gmail.com","Gayaneet","test123","test123");
//    Assert.assertTrue(actualtext.contains("Dear"),"Registration attempt failed");

    HomePageUpdated Homepage = new HomePageUpdated(driver);
    String actualtext = Homepage.selectRegisterMenu()
                .setfirstname("Gayanee")
                .setlastname("Thennakoon")
                .seteMail("testemail@gmail.com")
                .selectCounrty()
                .setUsername("Gayaneet")
                .setPassword("test123")
                .setConfirmPassword("test123")
                .submit()
                .RegisterSuccesstext();

//    RegisterSuccessPageUpdated registerSuccessPage =new RegisterSuccessPageUpdated(driver);
//    String actualtext =registerSuccessPage.RegisterSuccesstext();
    Assert.assertTrue(actualtext.contains("Dear"),"Registration attempt failed");

    }
//Method 1
//    public void registeruser(){
//        HomePageUpdated Homepage = new HomePageUpdated(driver);
//        Homepage.registerbut.click();
//
//        RegisterPageUpdated RegisterPage = new RegisterPageUpdated(driver);
//        RegisterPage.setfirstname("Gayanee");
//        RegisterPage.setlastname("Thennakoon");
//        RegisterPage.seteMail("testemail@gmail.com");
//        RegisterPage.selectCounrty();
//        RegisterPage.setUsername("Gayaneet");
//        RegisterPage.setPassword("test123");
//        RegisterPage.setConfirmPassword("test123");
//        RegisterPage.submit();
//    }
//
//    public void verifyRegistrationSucess(){
//        RegisterSuccessPageUpdated registerSuccessPage =new RegisterSuccessPageUpdated(driver);
//        String actualtext =registerSuccessPage.RegisterSuccesstext();
//        Assert.assertTrue(actualtext.contains("Dear"),"Registration attempt failed");
//    }


    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
