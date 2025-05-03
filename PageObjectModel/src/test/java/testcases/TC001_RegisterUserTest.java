package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;
import pages.RegisterSuccessPage;

import java.time.Duration;

public class TC001_RegisterUserTest {
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
    HomePage Homepage = new HomePage(driver);
    Homepage.selectRegisterMenu();

    RegisterPage RegisterPage = new RegisterPage(driver);
    RegisterPage.setfirstname("Gayanee");
    RegisterPage.setlastname("Thennakoon");
    RegisterPage.seteMail("testemail@gmail.com");
    RegisterPage.selectCounrty();
    RegisterPage.setUsername("Gayaneet");
    RegisterPage.setPassword("test123");
    RegisterPage.setConfirmPassword("test123");
    RegisterPage.submit();

    RegisterSuccessPage registerSuccessPage =new RegisterSuccessPage(driver);
    String actualtext =registerSuccessPage.RegisterSuccesstext();
    Assert.assertTrue(actualtext.contains("Dear"),"Registration attempt failed");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
